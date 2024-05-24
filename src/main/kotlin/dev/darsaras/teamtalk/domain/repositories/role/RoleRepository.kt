package dev.darsaras.teamtalk.domain.repositories.role

import dev.darsaras.teamtalk.domain.models.role.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long> {
}