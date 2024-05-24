package dev.darsaras.teamtalk.domain.models.user.requests

import dev.darsaras.teamtalk.domain.models.role.Role
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

data class UserRequest(
    val id : Long,
    val name : String,
    val lastname : String,
    val username : String,
    val email : String,
    val role : Role,
    val createdAt : LocalDateTime
)
