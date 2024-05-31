package dev.darsaras.teamtalk.infraestructure.app.channel

import dev.darsaras.teamtalk.application.services.channel.ChannelService
import dev.darsaras.teamtalk.domain.models.channel.requests.ChannelRequest
import dev.darsaras.teamtalk.domain.models.channel.responses.ChannelResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Channel",
    description = "Endpoints for Channel"
)
@Validated
@RestController
@RequestMapping("/channel")
class ChannelController(private val channelService: ChannelService) {

    @PostMapping("/create")
    @Operation(summary = "Endpoint for creating Channel given group id and ChannelRequest")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201" , description = "If Channel was successfully created"),
            ApiResponse(responseCode = "400", description = "If some field of ChannelRequest was missing"),
            ApiResponse(responseCode = "404", description = "If Group with given id was not found"),
            ApiResponse(responseCode = "406", description = "If ChannelRequest was null")
        ]
    )
    fun createChannel( @RequestParam id : Long , @Valid @RequestBody request : ChannelRequest?) : ResponseEntity<Unit> {
        return if ( request == null ) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else channelService.createChannel(id,request)
    }

    @GetMapping("/get")
    @Operation(summary = "Endpoint for getting Channel given an id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Channel was successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Channel was found with given id")
        ]
    )
    fun getChannel( @RequestParam id : Long ): ResponseEntity<ChannelResponse> {
        return channelService.getChannel(id)
    }

    @GetMapping("/all")
    @Operation(summary = "Endpoint for getting all Channels given a Group id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Channels were successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Group was found with given id")
        ]
    )
    fun getAllChannels( @RequestParam id : Long ): ResponseEntity<List<ChannelResponse>> {
        return channelService.getAllChannels(id)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint for deleting Channel given an id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Channel was successfully deleted"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Channel was found with given id")
        ]
    )
    fun deleteChannel( @RequestParam id : Long ): ResponseEntity<Unit> {
        return channelService.deleteChannel(id)
    }

    @PatchMapping("/update/name")
    @Operation(summary = "Endpoint for updating Channel name given an id and new name")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Channel was successfully deleted"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Channel was found with given id")
        ]
    )
    fun changeChannelName( @RequestParam id : Long , @RequestParam name : String ): ResponseEntity<Unit> {
        return channelService.changeChannelName(id,name)
    }

}