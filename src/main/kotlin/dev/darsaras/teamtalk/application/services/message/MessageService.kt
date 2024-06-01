package dev.darsaras.teamtalk.application.services.message

import dev.darsaras.teamtalk.domain.models.message.requests.MessageRequest
import dev.darsaras.teamtalk.domain.models.message.responses.MessageResponse
import org.springframework.http.ResponseEntity

interface MessageService {
    fun createMessage( userId : Long, channelId : Long , request : MessageRequest ) : ResponseEntity<Unit>
    fun getMessage( id : Long ) : ResponseEntity<MessageResponse>
    fun getAllMessages( channelId : Long ) : ResponseEntity<List<MessageResponse>>
    fun deleteMessage( id : Long ) : ResponseEntity<Unit>
    fun changeDescription( id : Long , description : String ) : ResponseEntity<Unit>
}