package dev.darsaras.teamtalk.application.services.user

import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.requests.UserUpdateRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import org.springframework.http.ResponseEntity

interface UserService {
    fun login(  ) : ResponseEntity<UserResponse>
    fun createUser(request : UserRequest) : ResponseEntity<Unit>
    fun getUser(id : Long) : ResponseEntity<UserResponse>
    fun updateUser( request: UserUpdateRequest , id : Long ): ResponseEntity<Unit>
    fun deleteUser( id: Long ) : ResponseEntity<Unit>
    fun updatePassword( id : Long , password : String ) : ResponseEntity<Unit>
}