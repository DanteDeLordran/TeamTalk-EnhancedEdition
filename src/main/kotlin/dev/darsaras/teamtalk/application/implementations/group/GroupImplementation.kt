package dev.darsaras.teamtalk.application.implementations.group

import dev.darsaras.teamtalk.application.exceptions.ResourceNotFoundException
import dev.darsaras.teamtalk.application.implementations.channel.ChannelImplementation
import dev.darsaras.teamtalk.application.services.group.GroupService
import dev.darsaras.teamtalk.domain.models.group.Group
import dev.darsaras.teamtalk.domain.models.group.requests.GroupRequest
import dev.darsaras.teamtalk.domain.models.group.responses.GroupResponse
import dev.darsaras.teamtalk.domain.models.user.User
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import dev.darsaras.teamtalk.domain.repositories.channel.ChannelRepository
import dev.darsaras.teamtalk.domain.repositories.group.GroupRepository
import dev.darsaras.teamtalk.domain.repositories.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class GroupImplementation(private val groupRepository: GroupRepository, private val userRepository: UserRepository,
                          private val channelRepository: ChannelRepository, private val channelImplementation: ChannelImplementation
) : GroupService{

    override fun createGroup(id: Long, request: GroupRequest): ResponseEntity<Unit> {
        val user = userRepository.findById(id)
        if (user.isEmpty) throw ResourceNotFoundException(resourceName = "User", fieldName = "id", fieldValue = "$id")
        val foundGroup = groupRepository.findByName(request.name)
        if (foundGroup != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }else{
            val group = Group(
                name = request.name,
                createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime(),
                users = mutableSetOf(user.get())
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
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
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
            val channels = channelRepository.findAllByGroupId(id) ?: listOf()
            for (channel in channels){
                channelImplementation.deleteChannel(channel.id ?: 0)
            }
            groupRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
    }

    override fun changeGroupName(id: Long, name: String): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            group.get().name = name
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
    }

    override fun addMembers(id: Long, members: Set<UserResponse>): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        val users = mutableSetOf<User>()
        for (response in members){
            val user = userRepository.findById(response.id)
            if (user.isPresent) users.add(user.get())
        }
        if (group.isPresent){
            group.get().users.addAll(users)
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
    }

    override fun removeMembers(id: Long, members: Set<UserResponse>): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        val users = mutableSetOf<User>()
        for (response in members){
            val user = userRepository.findById(response.id)
            if (user.isPresent) users.add(user.get())
        }
        if (group.isPresent){
            group.get().users.removeAll(users)
            groupRepository.save(group.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
    }
}