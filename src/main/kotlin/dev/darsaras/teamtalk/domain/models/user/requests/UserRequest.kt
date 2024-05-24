package dev.darsaras.teamtalk.domain.models.user.requests

import dev.darsaras.teamtalk.domain.models.role.Role
import java.time.LocalDateTime

data class UserRequest(
    val name : String,
    val lastname : String,
    val username : String,
    val password : String,
    val email : String
)
