package dev.darsaras.teamtalk.infraestructure.auth.user

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.requests.UserUpdateRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "User",
    description = "Controller for User module"
)
@Validated
@RestController
@RequestMapping("/auth")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    @Operation(summary = "Controller for creating User from UserRequest")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "If User was successfully created"),
            ApiResponse(responseCode = "400" , description = "If some property is missing"),
            ApiResponse(responseCode = "406" , description = "If request is null")
        ]
    )
    fun createUser( @Valid @RequestBody request: UserRequest? ): ResponseEntity<Unit> {
        return if ( request == null ) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else userService.createUser(request)
    }

    @GetMapping("/get")
    @Operation(summary = "Controller for get User from given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If User was successfully returned"),
            ApiResponse(responseCode = "404" , description = "If User was not found with given id")
        ]
    )
    fun getUser( @RequestParam id : Long ) : ResponseEntity<UserResponse> {
        return userService.getUser(id)
    }

    @PutMapping("/update")
    @Operation(summary = "Controller for updating User from UserUpdateRequest")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If User was successfully updated"),
            ApiResponse(responseCode = "400" , description = "If some property in Request is missing"),
            ApiResponse(responseCode = "404", description = "If User was not found with given id"),
            ApiResponse(responseCode = "406" , description = "If request is null")
        ]
    )
    fun updateUser( @Valid @RequestBody request : UserUpdateRequest? , @RequestParam id : Long) : ResponseEntity<Unit> {
        return if ( request == null ) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else userService.updateUser(request , id)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Controller for deleting User from given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If User was successfully deleted"),
            ApiResponse(responseCode = "404", description = "If User was not found with given id")
        ]
    )
    fun deleteUser( @RequestParam id : Long ) : ResponseEntity<Unit> {
        return userService.deleteUser(id)
    }

    @PatchMapping("/update/password")
    @Operation(summary = "Controller for updating User's password")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If password was successfully updated"),
            ApiResponse(responseCode = "404", description = "If User was not found with given id")
        ]
    )
    fun updatePassword( @RequestParam id : Long , @RequestParam password : String ): ResponseEntity<Unit> {
        return userService.updatePassword(id,password)
    }

}