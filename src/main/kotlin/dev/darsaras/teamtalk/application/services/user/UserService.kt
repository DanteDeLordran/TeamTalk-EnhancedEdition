package dev.darsaras.teamtalk.application.services.user

import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import org.springframework.http.ResponseEntity

interface UserService {
    fun createUser(request : UserRequest) : ResponseEntity<Unit>
    fun getUser(id : Long) : ResponseEntity<UserResponse>
    fun updateUser( request: UserRequest , id : Long ): ResponseEntity<Unit>
    fun deleteUser( id: Long ) : ResponseEntity<Unit>
}