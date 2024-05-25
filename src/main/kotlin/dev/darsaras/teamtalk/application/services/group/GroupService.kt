package dev.darsaras.teamtalk.application.services.group

import dev.darsaras.teamtalk.domain.models.group.requests.GroupRequest
import dev.darsaras.teamtalk.domain.models.user.User
import org.springframework.http.ResponseEntity

interface GroupService {
    fun createGroup( request : GroupRequest ) : ResponseEntity<Unit>
    fun getGroup( id : Long ) : ResponseEntity<Unit>
    fun getAllGroups( userId : Long ) : ResponseEntity<Unit>
    fun deleteGroup( id : Long ) : ResponseEntity<Unit>
    fun changeGroupName( id : Long , name : String ) : ResponseEntity<Unit>
    fun addMembers( id : Long , members : Set<User> ) : ResponseEntity<Unit>
    fun removeMembers( id : Long , members : Set<User> ) : ResponseEntity<Unit>
}