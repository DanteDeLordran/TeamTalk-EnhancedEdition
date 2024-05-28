package dev.darsaras.teamtalk.domain.models.user.requests

data class UserUpdateRequest(
    val name : String,
    val lastname : String,
    val username : String,
    val email : String
)
