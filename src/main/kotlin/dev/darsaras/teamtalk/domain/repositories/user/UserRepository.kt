package dev.darsaras.teamtalk.domain.repositories.user

import dev.darsaras.teamtalk.domain.models.user.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>{
    fun findByEmail( email : String ) : User?
}