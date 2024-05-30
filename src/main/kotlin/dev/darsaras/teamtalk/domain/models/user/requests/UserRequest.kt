package dev.darsaras.teamtalk.domain.models.user.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Schema(
    description = "Request schema for User"
)
data class UserRequest(
    @Schema(example = "Dante")
    @field:NotEmpty(message = "Name can not be null or empty")
    val name : String,

    @Schema(example = "de Lordran")
    @field:NotEmpty(message = "Lastname can not be null or empty")
    @field:NotNull(message = "Lastname can not be null")
    val lastname : String,

    @Schema(example = "Lordran")
    @field:NotEmpty(message = "Username can not be null or empty")
    val username : String,

    @Schema(example = "asdasdasdasdsa")
    @field:NotEmpty(message = "Password can not be null or empty")
    val password : String,

    @Schema(example = "lordran@lordran.dev")
    @field:Email(message = "Email address not valid")
    @field:NotEmpty(message = "Email can not be null or empty")
    val email : String
)
