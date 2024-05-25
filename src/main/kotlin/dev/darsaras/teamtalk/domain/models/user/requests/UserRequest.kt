package dev.darsaras.teamtalk.domain.models.user.requests

data class UserRequest(
    val name : String,
    val lastname : String,
    val username : String,
    val password : String,
    val email : String
)
