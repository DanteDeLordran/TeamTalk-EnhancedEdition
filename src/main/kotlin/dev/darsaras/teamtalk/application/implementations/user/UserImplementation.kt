package dev.darsaras.teamtalk.application.implementations.user

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.repositories.user.UserRepository
import org.springframework.http.ResponseEntity

class UserImplementation( val userRepository: UserRepository ) : UserService{

    override fun createUser(request: UserRequest): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Long): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

}