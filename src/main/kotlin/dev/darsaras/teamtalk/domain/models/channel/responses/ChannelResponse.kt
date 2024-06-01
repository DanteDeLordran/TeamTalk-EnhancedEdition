package dev.darsaras.teamtalk.domain.models.channel.responses

import dev.darsaras.teamtalk.domain.models.group.Group
import java.time.LocalDateTime

data class ChannelResponse(
    val id : Long,
    val name : String,
    val createdAt : LocalDateTime
)
