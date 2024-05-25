package dev.darsaras.teamtalk.domain.models.group.responses

import java.time.LocalDateTime

data class GroupResponse(
    val id : Long,
    val name : String,
    val createdAt : LocalDateTime
)
