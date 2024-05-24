package dev.darsaras.teamtalk.domain.repositories.channel

import dev.darsaras.teamtalk.domain.models.channel.Channel
import org.springframework.data.repository.CrudRepository

interface ChannelRepository : CrudRepository<Channel, Long> {
}