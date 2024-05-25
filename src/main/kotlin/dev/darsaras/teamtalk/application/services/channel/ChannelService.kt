package dev.darsaras.teamtalk.application.services.channel

import dev.darsaras.teamtalk.domain.models.channel.requests.ChannelRequest
import org.springframework.http.ResponseEntity

interface ChannelService {
    fun createChannel( request : ChannelRequest ) : ResponseEntity<Unit>
    fun deleteChannel( id : Long ) : ResponseEntity<Unit>
    fun changeChannelName( id : Long , name : String ) : ResponseEntity<Unit>
}