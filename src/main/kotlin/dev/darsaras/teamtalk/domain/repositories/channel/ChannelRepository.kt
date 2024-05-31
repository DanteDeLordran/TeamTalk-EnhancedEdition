package dev.darsaras.teamtalk.domain.repositories.channel

import dev.darsaras.teamtalk.domain.models.channel.Channel
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity

interface ChannelRepository : CrudRepository<Channel, Long> {
    fun findAllByGroupId( id : Long ) : List<Channel>?
}