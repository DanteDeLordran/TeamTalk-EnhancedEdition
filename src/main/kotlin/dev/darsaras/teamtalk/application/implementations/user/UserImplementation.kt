package dev.darsaras.teamtalk.application.implementations.user

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.role.Role
import dev.darsaras.teamtalk.domain.models.user.User
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.repositories.role.RoleRepository
import dev.darsaras.teamtalk.domain.repositories.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class UserImplementation(private val userRepository: UserRepository, private val roleRepository: RoleRepository ) : UserService{

    override fun createUser(request: UserRequest): ResponseEntity<Unit> {

        val role : Role = roleRepository.findByName("ROLE_USER")

        val user = User(
            name = request.name,
            lastname = request.lastname,
            password = request.password,
            role = role,
            email =  request.email,
            username = request.username,
            createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime(),
            id = null
        )
        userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getUser(id: Long): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

}