package dev.darsaras.teamtalk.application.services.user

import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import org.springframework.http.ResponseEntity

interface UserService {

    fun createUser(request : UserRequest) : ResponseEntity<Unit>

    fun getUser(id : Long) : ResponseEntity<Unit>

}