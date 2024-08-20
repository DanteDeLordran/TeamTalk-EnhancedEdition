package dev.darsaras.teamtalk.application.services.user

import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.requests.UserUpdateRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication

interface UserService {
    fun login( authentication : Authentication ) : ResponseEntity<UserResponse>
    fun getUserPassword( email : String ) : String
    fun createUser(request : UserRequest) : ResponseEntity<Unit>
    fun getUserByEmail( email : String ) : ResponseEntity<UserResponse>
    fun getUser(id : Long) : ResponseEntity<UserResponse>
    fun updateUser( request: UserUpdateRequest , id : Long ): ResponseEntity<Unit>
    fun deleteUser( id: Long ) : ResponseEntity<Unit>
    fun updatePassword( id : Long , password : String ) : ResponseEntity<Unit>
}