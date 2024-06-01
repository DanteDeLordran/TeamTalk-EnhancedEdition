package dev.darsaras.teamtalk.application.implementations.channel

import dev.darsaras.teamtalk.application.exceptions.ResourceNotFoundException
import dev.darsaras.teamtalk.application.services.channel.ChannelService
import dev.darsaras.teamtalk.domain.models.channel.Channel
import dev.darsaras.teamtalk.domain.models.channel.requests.ChannelRequest
import dev.darsaras.teamtalk.domain.models.channel.responses.ChannelResponse
import dev.darsaras.teamtalk.domain.repositories.channel.ChannelRepository
import dev.darsaras.teamtalk.domain.repositories.group.GroupRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class ChannelImplementation(private val channelRepository: ChannelRepository , private val groupRepository: GroupRepository) : ChannelService{

    override fun createChannel(id: Long, request: ChannelRequest): ResponseEntity<Unit> {
        val group = groupRepository.findById(id)
        if (group.isPresent){
            val channel = Channel(
                name = request.name,
                group = group.get(),
                createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime()
            )
            channelRepository.save(channel)
            return ResponseEntity.status(HttpStatus.CREATED).build()
        }else throw ResourceNotFoundException(fieldName = "id", fieldValue = "$id", resourceName = "Group")
    }

    override fun getChannel(id: Long): ResponseEntity<ChannelResponse> {
        val channel = channelRepository.findById(id)
        if (channel.isPresent){
            val response = ChannelResponse(
                id= channel.get().id ?: 0,
                name = channel.get().name,
                group = channel.get().group,
                createdAt = channel.get().createdAt
            )
            return ResponseEntity.status(HttpStatus.OK).body(response)
        }else throw ResourceNotFoundException(fieldValue = "$id", resourceName = "Channel", fieldName = "id")
    }

    override fun getAllChannels(groupId: Long): ResponseEntity<List<ChannelResponse>> {
        val channels = channelRepository.findAllByGroupId(groupId) ?: throw ResourceNotFoundException(resourceName = "Group", fieldValue = "$groupId", fieldName = "id")
        val responses : List<ChannelResponse> = channels.map {
            c ->
            ChannelResponse(
                name = c.name,
                group = c.group,
                id = c.id ?: 0,
                createdAt = c.createdAt
            )
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses)
    }

    override fun deleteChannel(id: Long): ResponseEntity<Unit> {
        val channel = channelRepository.findById(id)
        if (channel.isPresent){
            channelRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(resourceName = "Group", fieldValue = "$id", fieldName = "id")
    }

    override fun changeChannelName(id: Long, name: String): ResponseEntity<Unit> {
        val channel = channelRepository.findById(id)
        if (channel.isPresent){
            channel.get().name = name
            channelRepository.save(channel.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(resourceName = "Group", fieldValue = "$id", fieldName = "id")
    }
}