package dev.darsaras.teamtalk.application.exceptions.responses

import java.time.LocalDateTime

data class ErrorResponse(
    val apiPath : String,
    val errorMessage : String,
    val errorDateTime : LocalDateTime
)
