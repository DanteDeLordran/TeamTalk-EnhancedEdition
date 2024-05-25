package dev.darsaras.teamtalk.domain.models.user.responses

import dev.darsaras.teamtalk.domain.models.role.Role

data class UserResponse(
    val id : Long,
    val name : String,
    val lastname : String,
    val username : String,
    val email : String,
    val role : Role
)
