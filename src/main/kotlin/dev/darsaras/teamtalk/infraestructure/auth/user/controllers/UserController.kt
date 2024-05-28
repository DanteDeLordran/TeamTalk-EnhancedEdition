package dev.darsaras.teamtalk.infraestructure.auth.user.controllers

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser( @RequestBody userRequest: UserRequest ): ResponseEntity<Unit> {
        return userService.createUser(userRequest)
    }

    @GetMapping("/get")
    fun getUser( @RequestParam id : Long ) : ResponseEntity<UserResponse> {
        return userService.getUser(id)
    }

}