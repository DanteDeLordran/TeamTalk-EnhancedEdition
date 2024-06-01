package dev.darsaras.teamtalk.domain.models.message.responses

import dev.darsaras.teamtalk.domain.models.channel.Channel
import dev.darsaras.teamtalk.domain.models.user.User
import java.time.LocalDateTime

data class MessageResponse(
    val id : Long,
    val user : User,
    val description : String,
    var createdAt : LocalDateTime
)
