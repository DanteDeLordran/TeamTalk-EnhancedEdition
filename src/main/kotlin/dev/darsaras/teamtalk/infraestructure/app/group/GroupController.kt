package dev.darsaras.teamtalk.infraestructure.app.group

import dev.darsaras.teamtalk.application.services.group.GroupService
import dev.darsaras.teamtalk.domain.models.group.requests.GroupRequest
import dev.darsaras.teamtalk.domain.models.group.responses.GroupResponse
import dev.darsaras.teamtalk.domain.models.user.responses.UserResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(
    name = "Group",
    description = "Endpoints for Group module"
)
@Validated
@RestController
@RequestMapping("/group")
class GroupController( private val groupService: GroupService) {

    @PostMapping("/create")
    @Operation(summary = "Endpoint for creating Group from GroupRequest")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201" , description = "If Group was successfully created"),
            ApiResponse(responseCode = "400", description = "If some field is missing or not valid"),
            ApiResponse(responseCode = "406" , description = "If GroupRequest is null")
        ]
    )
    fun createGroup( @RequestParam id : Long, @Valid @RequestBody request : GroupRequest? ) : ResponseEntity<Unit> {
        return if (request == null) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else groupService.createGroup(id,request)
    }

    @GetMapping("/get")
    @Operation(summary = "Endpoint for getting Group from given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If Group was successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Group was not found with given id")
        ]
    )
    fun getGroup( @RequestParam id : Long ): ResponseEntity<GroupResponse> {
        return groupService.getGroup(id)
    }

    @GetMapping("/all")
    @Operation(summary = "Endpoint for getting all Groups from given user id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If Groups were successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Groups were not found with given user id")
        ]
    )
    fun getAllGroups( @RequestParam id : Long ): ResponseEntity<List<GroupResponse>> {
        return groupService.getAllGroups(id)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint for deleting Group from given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If Group was successfully deleted"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Group was not found with given id")
        ]
    )
    fun deleteGroup( @RequestParam id : Long ): ResponseEntity<Unit> {
        return groupService.deleteGroup(id)
    }

    @PatchMapping("/change/name")
    @Operation(summary = "Endpoint for updating Group name from given group id and new name")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If Group was successfully deleted"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Group was not found with given id")
        ]
    )
    fun changeGroupName( @RequestParam id : Long , @RequestParam name : String ): ResponseEntity<Unit> {
        return groupService.changeGroupName(id, name)
    }

    @PutMapping("/members/add")
    @Operation(summary = "Endpoint for adding members to Group from given group id and member list")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If members were successfully added"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Group was not found with given id")
        ]
    )
    fun addMembers( @RequestParam id: Long , @RequestBody members : Set<UserResponse>? ): ResponseEntity<Unit> {
        return if (members == null) groupService.addMembers(id, setOf())
        else groupService.addMembers(id,members)
    }

    @PutMapping("/members/remove")
    @Operation(summary = "Endpoint for removing members from Group from given group id and member list")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200" , description = "If members were successfully removed"),
            ApiResponse(responseCode = "400", description = "If param is missing or not valid"),
            ApiResponse(responseCode = "404", description = "If Group was not found with given id")
        ]
    )
    fun removeMembers( @RequestParam id: Long, @RequestBody members: Set<UserResponse>? ): ResponseEntity<Unit> {
        return if (members == null)  groupService.removeMembers(id, setOf())
        else groupService.removeMembers(id,members)
    }

}