package dev.darsaras.teamtalk.infraestructure.config

import dev.darsaras.teamtalk.application.exceptions.ResourceNotFoundException
import dev.darsaras.teamtalk.application.exceptions.responses.ErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.ZoneId
import java.time.ZonedDateTime

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val validationErrors = mutableMapOf<String,String>()
        val validationErrorList : MutableList<ObjectError> = ex.bindingResult.allErrors
        validationErrorList.forEach{
            e ->
            val fieldName = (e as FieldError).field
            val validationMsg = e.defaultMessage
            validationErrors[fieldName] = validationMsg ?: "No message provided"
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(e : Exception , webRequest: WebRequest): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            apiPath = webRequest.getDescription(false),
            errorMessage = e.message ?: "No message provided",
            errorDateTime = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e : Exception , webRequest: WebRequest): ResponseEntity<ErrorResponse>{
        val response = ErrorResponse(
            apiPath = webRequest.getDescription(false),
            errorMessage = e.message ?: "No message provided",
            errorDateTime = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

}