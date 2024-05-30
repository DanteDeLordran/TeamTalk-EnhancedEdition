package dev.darsaras.teamtalk.application.services.channel

import dev.darsaras.teamtalk.domain.models.channel.requests.ChannelRequest
import dev.darsaras.teamtalk.domain.models.channel.responses.ChannelResponse
import org.springframework.http.ResponseEntity

interface ChannelService {
    fun createChannel( id : Long , request : ChannelRequest ) : ResponseEntity<Unit>
    fun getChannel( id : Long ) : ResponseEntity<ChannelResponse>
    fun getAllChannels( groupId : Long ) : ResponseEntity<List<ChannelResponse>>
    fun deleteChannel( id : Long ) : ResponseEntity<Unit>
    fun changeChannelName( id : Long , name : String ) : ResponseEntity<Unit>
}