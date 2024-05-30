package dev.darsaras.teamtalk.domain.models.channel.requests

import dev.darsaras.teamtalk.domain.models.group.Group
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "ChannelRequest for Channel entity")
data class ChannelRequest(
    @Schema(example = "Kotlin channel")
    @field:NotEmpty(message = "Name can not be empty or null")
    @field:Size(min = 1, message = "Channel name must have at least one char")
    val name : String,
)
