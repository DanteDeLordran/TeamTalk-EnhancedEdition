package dev.darsaras.teamtalk.domain.models.user.responses

import dev.darsaras.teamtalk.domain.models.role.Role

data class UserResponse(
    val id : Long,
    var name : String,
    var lastname : String,
    var username : String,
    var email : String,
    var role : Role
)
