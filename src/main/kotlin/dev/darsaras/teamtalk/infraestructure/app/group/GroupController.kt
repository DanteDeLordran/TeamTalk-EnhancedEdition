package dev.darsaras.teamtalk.infraestructure.app.group

import dev.darsaras.teamtalk.application.services.group.GroupService
import dev.darsaras.teamtalk.domain.models.group.requests.GroupRequest
import dev.darsaras.teamtalk.domain.models.group.responses.GroupResponse
import dev.darsaras.teamtalk.domain.models.user.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/group")
class GroupController( private val groupService: GroupService) {

    @PostMapping("/create")
    fun createGroup( @RequestBody request : GroupRequest ) : ResponseEntity<Unit> {
        return groupService.createGroup(request)
    }

    @GetMapping("/get")
    fun getGroup( @RequestParam id : Long ): ResponseEntity<GroupResponse> {
        return groupService.getGroup(id)
    }

    @GetMapping("/all")
    fun getAllGroups( @RequestParam id : Long ): ResponseEntity<List<GroupResponse>> {
        return groupService.getAllGroups(id)
    }

    @DeleteMapping("/delete")
    fun deleteGroup( @RequestParam id : Long ): ResponseEntity<Unit> {
        return groupService.deleteGroup(id)
    }

    @PatchMapping("/change/name")
    fun changeGroupName( @RequestParam id : Long , @RequestParam name : String ): ResponseEntity<Unit> {
        return groupService.changeGroupName(id, name)
    }

    @PutMapping("/members/add")
    fun addMembers( @RequestParam id: Long , @RequestBody members : Set<User> ): ResponseEntity<Unit> {
        return groupService.addMembers(id,members)
    }

    @PutMapping("/members/remove")
    fun removeMembers( @RequestParam id: Long, @RequestBody members: Set<User> ): ResponseEntity<Unit> {
        return groupService.removeMembers(id,members)
    }

}