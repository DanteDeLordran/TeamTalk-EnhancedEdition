package dev.darsaras.teamtalk.application.implementations.user

import dev.darsaras.teamtalk.application.exceptions.ResourceNotFoundException
import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.role.Role
import dev.darsaras.teamtalk.domain.models.user.User
import dev.darsaras.teamtalk.domain.models.user.requests.UserRequest
import dev.darsaras.teamtalk.domain.models.user.requests.UserUpdateRequest
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import dev.darsaras.teamtalk.domain.repositories.role.RoleRepository
import dev.darsaras.teamtalk.domain.repositories.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class UserImplementation(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder) : UserService{

    override fun login(): ResponseEntity<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserPassword(email: String): String {
        val user = userRepository.findByEmail(email) ?: throw ResourceNotFoundException(resourceName = "User", fieldName = "email", fieldValue = email)
        return user.password
    }

    override fun createUser(request: UserRequest): ResponseEntity<Unit> {

        val role : Role = roleRepository.findByName("ROLE_USER")

        val user = User(
            name = request.name,
            lastname = request.lastname,
            password = passwordEncoder.encode(request.password),
            role = role,
            email =  request.email,
            username = request.username,
            createdAt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")).toLocalDateTime()
        )
        userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getUserByEmail(email: String): ResponseEntity<UserResponse> {
        val user = userRepository.findByEmail(email) ?: throw ResourceNotFoundException(resourceName = "User", fieldName = "email", fieldValue = email)
        val response = UserResponse(
            id = user.id ?: 1,
            name = user.name,
            lastname = user.lastname,
            email = user.email,
            username = user.username,
            role = user.role
        )
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    override fun getUser(id: Long): ResponseEntity<UserResponse> {
        val user = userRepository.findById(id)
        if (user.isPresent){
            val response = UserResponse(
                id = user.get().id ?: 1,
                name = user.get().name,
                lastname = user.get().lastname,
                username = user.get().username,
                email = user.get().email,
                role = user.get().role
            )
            return ResponseEntity.status(HttpStatus.OK).body(response)
        }else throw ResourceNotFoundException(resourceName = "User", fieldName = "id", fieldValue = "$id")
    }

    override fun updateUser(request: UserUpdateRequest, id: Long): ResponseEntity<Unit> {
        val user = userRepository.findById(id)
        if (user.isPresent){

            user.get().name = request.name
            user.get().lastname = request.lastname
            user.get().username = request.username
            user.get().email = request.email

            userRepository.save(user.get())

            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(resourceName = "User", fieldName = "id", fieldValue = "$id")
    }

    override fun deleteUser(id: Long): ResponseEntity<Unit> {
        val user = userRepository.findById(id)
        if (user.isPresent){
            userRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(resourceName = "User", fieldName = "id", fieldValue = "$id")
    }

    override fun updatePassword(id: Long, password: String): ResponseEntity<Unit> {
        val user = userRepository.findById(id)
        if (user.isPresent){
            user.get().password = password
            userRepository.save(user.get())
            return ResponseEntity.status(HttpStatus.OK).build()
        }else throw ResourceNotFoundException(resourceName = "User", fieldName = "id", fieldValue = "$id")
    }

}