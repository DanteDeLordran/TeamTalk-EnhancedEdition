package dev.darsaras.teamtalk.infraestructure.auth.user.controllers

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.requests.UserUpdateRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PutMapping("/update")
    fun updateUser( @RequestBody request : UserUpdateRequest , @RequestParam id : Long) : ResponseEntity<Unit> {
        return userService.updateUser(request , id)
    }

    @DeleteMapping("/delete")
    fun deleteUser( @RequestParam id : Long ) : ResponseEntity<Unit> {
        return userService.deleteUser(id)
    }

    @PatchMapping("/update/password")
    fun updatePassword( id : Long , password : String ): ResponseEntity<Unit> {
        return userService.updatePassword(id,password)
    }

}