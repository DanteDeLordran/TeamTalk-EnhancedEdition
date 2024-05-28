package dev.darsaras.teamtalk.application.implementations.group

import dev.darsaras.teamtalk.application.services.group.GroupService
import dev.darsaras.teamtalk.domain.models.group.Group
import dev.darsaras.teamtalk.domain.models.group.requests.GroupRequest
import dev.darsaras.teamtalk.domain.models.group.responses.GroupResponse
import dev.darsaras.teamtalk.domain.models.user.User
import dev.darsaras.teamtalk.domain.repositories.group.GroupRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class GroupImplementation(private val groupRepository: GroupRepository) : GroupService{

    override fun createGroup(request: GroupRequest): ResponseEntity<Unit> {

        val foundGroup = groupRepository.findByName(request.name)

        if (foundGroup != null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        }else{
            val group = Group(
                name = request.name,
                createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime(),
                users = mutableSetOf()
            )
            groupRepository.save(group)
            return ResponseEntity.status(HttpStatus.CREATED).build()
        }

    }

    override fun getGroup(id: Long): ResponseEntity<GroupResponse> {
        val group = groupRepository.findById(id)

        return if (group.isPresent){

            val response = GroupResponse(
                name = group.get().name,
                createdAt = group.get().createdAt,
                id = group.get().id ?: 0
            )

            ResponseEntity.status(HttpStatus.OK).body(response)
        }else ResponseEntity.status(HttpStatus.NOT_FOUND).build()

    }

    override fun getAllGroups(userId: Long): ResponseEntity<List<GroupResponse>> {
        val groups = groupRepository.findAllByUsersId(userId) ?: listOf()

        val responses : List<GroupResponse> = groups.map {
            group ->
            GroupResponse(
                id = group.id ?: 0,
                name = group.name,
                createdAt = group.createdAt
            )
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses)
    }

    override fun deleteGroup(id: Long): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            groupRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).build()
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    override fun changeGroupName(id: Long, name: String): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            group.get().name = name
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    override fun addMembers(id: Long, members: Set<User>): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            group.get().users.addAll(members)
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    override fun removeMembers(id: Long, members: Set<User>): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            group.get().users.removeAll(members)
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}