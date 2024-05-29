package dev.darsaras.teamtalk.application.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    private val resourceName : String,
    private val fieldName : String,
    private val fieldValue : String
) : RuntimeException ("$resourceName not found given the input data $fieldName $fieldValue") {
}