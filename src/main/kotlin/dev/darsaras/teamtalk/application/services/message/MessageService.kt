package dev.darsaras.teamtalk.application.services.message

import dev.darsaras.teamtalk.domain.models.message.requests.MessageRequest
import org.springframework.http.ResponseEntity

interface MessageService {
    fun createMessage( request : MessageRequest ) : ResponseEntity<Unit>
    fun getMessage( id : Long ) : ResponseEntity<Unit>
    fun getAllMessages( channelId : Long ) : ResponseEntity<Unit>
    fun deleteMessage( id : Long ) : ResponseEntity<Unit>
    fun changeDescription( id : Long , description : String ) : ResponseEntity<Unit>
}