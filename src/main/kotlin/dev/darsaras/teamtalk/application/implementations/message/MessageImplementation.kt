package dev.darsaras.teamtalk.application.implementations.message

import dev.darsaras.teamtalk.application.exceptions.ResourceNotFoundException
import dev.darsaras.teamtalk.application.services.message.MessageService
import dev.darsaras.teamtalk.domain.models.message.Message
import dev.darsaras.teamtalk.domain.models.message.requests.MessageRequest
import dev.darsaras.teamtalk.domain.models.message.responses.MessageResponse
import dev.darsaras.teamtalk.domain.repositories.channel.ChannelRepository
import dev.darsaras.teamtalk.domain.repositories.message.MessageRepository
import dev.darsaras.teamtalk.domain.repositories.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class MessageImplementation(private val messageRepository: MessageRepository, private val userRepository: UserRepository, private val channelRepository: ChannelRepository) : MessageService{

    override fun createMessage(userId: Long, channelId: Long, request: MessageRequest): ResponseEntity<Unit> {
        val user = userRepository.findById(userId)
        if (user.isEmpty) throw ResourceNotFoundException(resourceName = "User", fieldName = "id" , fieldValue = "$userId")
        val channel = channelRepository.findById(channelId)
        if (channel.isEmpty) throw ResourceNotFoundException(resourceName = "Channel", fieldName = "id" , fieldValue = "$channelId")

        val message = Message(
            description = request.description,
            createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime(),
            user = user.get(),
            channel = channel.get()
        )
        messageRepository.save(message)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getMessage(id: Long): ResponseEntity<MessageResponse> {
        val message = messageRepository.findById(id)
        if (message.isEmpty) throw ResourceNotFoundException(resourceName = "Message", fieldName = "id" , fieldValue = "$id")
        val response = MessageResponse(
            id = message.get().id ?: 0,
            user = message.get().user,
            description = message.get().description,
            createdAt = message.get().createdAt
        )
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    override fun getAllMessages(channelId: Long): ResponseEntity<List<MessageResponse>> {
        val messages = messageRepository.findAllByChannelId(channelId) ?: throw ResourceNotFoundException(resourceName = "Channel", fieldName = "id" , fieldValue = "$channelId")
        val responses : List<MessageResponse> = messages.map {
            m ->
            MessageResponse(
                id = m.id ?: 0,
                user = m.user,
                description = m.description,
                createdAt = m.createdAt
            )
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses)
    }

    override fun deleteMessage(id: Long): ResponseEntity<Unit> {
        val message = messageRepository.findById(id)
        if (message.isPresent) {
            messageRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).build()
        } else throw ResourceNotFoundException(resourceName = "Message", fieldName = "id" , fieldValue = "$id")
    }

    override fun changeDescription(id: Long, description: String): ResponseEntity<Unit> {
        val message = messageRepository.findById(id)
        if (message.isPresent) {
            message.get().description = description
            messageRepository.save(message.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        } else throw ResourceNotFoundException(resourceName = "Message", fieldName = "id" , fieldValue = "$id")
    }
}