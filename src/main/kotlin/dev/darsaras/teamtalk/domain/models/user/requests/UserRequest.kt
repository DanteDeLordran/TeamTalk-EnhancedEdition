package dev.darsaras.teamtalk.domain.models.user.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Schema(
    description = "Request schema for User"
)
data class UserRequest(
    @Schema(example = "Dante")
    @NotEmpty(message = "Name can not be null or empty")
    val name : String,

    @Schema(example = "de Lordran")
    @NotEmpty(message = "Lastname can not be null or empty")
    val lastname : String,

    @Schema(example = "Lordran")
    @NotEmpty(message = "Username can not be null or empty")
    val username : String,

    @Schema(example = "asdasdasdasdsa")
    @NotEmpty(message = "Password can not be null or empty")
    val password : String,

    @Schema(example = "lordran@lordran.dev")
    @Email(message = "Email address not valid")
    @NotEmpty(message = "Email can not be null or empty")
    val email : String
)
