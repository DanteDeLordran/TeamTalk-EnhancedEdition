package dev.darsaras.teamtalk.domain.repositories.group

import dev.darsaras.teamtalk.domain.models.group.Group
import org.springframework.data.repository.CrudRepository

interface GroupRepository : CrudRepository<Group, Long>{
    fun findByName( name : String ) : Group?
    fun findAllByUsersId( id : Long ) : List<Group>?
}