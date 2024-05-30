package dev.darsaras.teamtalk.domain.models.user.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Schema(
    description = "Request schema for User update"
)
data class UserUpdateRequest(
    @Schema(example = "Dante")
    @field:NotEmpty(message = "Name can not be null or empty")
    val name : String,

    @Schema(example = "de Lordran")
    @field:NotEmpty(message = "Lastname can not be null or empty")
    val lastname : String,

    @Schema(example = "Lordran")
    @field:NotEmpty(message = "Username can not be null or empty")
    val username : String,

    @Schema(example = "lordran@lordran.dev")
    @field:Email(message = "Email address not valid")
    @field:NotEmpty(message = "Email can not be null or empty")
    val email : String
)
