package dev.darsaras.teamtalk.domain.models.message.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(description = "Message request for Message entity")
data class MessageRequest(
    @Schema(example = "Good morning mates!")
    @field:NotEmpty(message = "Description can not be empty")
    @field:Size(min = 1 , max = 400)
    val description : String
)
