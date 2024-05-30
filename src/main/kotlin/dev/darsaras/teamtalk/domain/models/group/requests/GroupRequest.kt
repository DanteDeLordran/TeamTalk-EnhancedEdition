package dev.darsaras.teamtalk.domain.models.group.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(description = "Request schema for Group")
data class GroupRequest(
    @Schema(example = "Programming group")
    @field:NotEmpty(message = "Name can not be null or empty")
    @field:Size(message = "Minimum length of 1 char", min = 1)
    val name : String
)