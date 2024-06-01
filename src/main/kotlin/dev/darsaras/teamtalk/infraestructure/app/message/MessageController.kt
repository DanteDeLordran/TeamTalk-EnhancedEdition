package dev.darsaras.teamtalk.infraestructure.app.message

import dev.darsaras.teamtalk.application.services.message.MessageService
import dev.darsaras.teamtalk.domain.models.message.requests.MessageRequest
import dev.darsaras.teamtalk.domain.models.message.responses.MessageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(
    name = "Message",
    description = "Endpoints for Message module"
)
@Validated
@RestController
@RequestMapping("/message")
class MessageController(private val messageService: MessageService) {

    @PostMapping("/create")
    @Operation(summary = "Endpoint for creating Message given user id , channel id and MessageRequest")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201" , description = "If Message was successfully created"),
            ApiResponse(responseCode = "400", description = "If some param was null or invalid"),
            ApiResponse(responseCode = "404", description = "If User or Channel with given id were not found"),
            ApiResponse(responseCode = "406", description = "If MessageRequest was null")
        ]
    )
    fun createMessage( @RequestParam userId : Long , @RequestParam channelId : Long , @RequestBody request : MessageRequest? ): ResponseEntity<Unit> {
        return if (request == null) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else messageService.createMessage( userId, channelId, request )
    }

    @GetMapping("/get")
    @Operation(summary = "Endpoint for getting Message given an id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Message was successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Message was found with given id")
        ]
    )
    fun getMessage( @RequestParam id : Long ): ResponseEntity<MessageResponse> {
        return messageService.getMessage(id)
    }

    @GetMapping("/all")
    @Operation(summary = "Endpoint for getting all Messages given a Channel id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Messages were successfully returned"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Channel was found with given id")
        ]
    )
    fun getAllMessages( @RequestParam id : Long ): ResponseEntity<List<MessageResponse>> {
        return messageService.getAllMessages(id)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint for deleting Message given an id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Message was successfully deleted"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Message was found with given id")
        ]
    )
    fun deleteMessage( @RequestParam id : Long ): ResponseEntity<Unit> {
        return messageService.deleteMessage(id)
    }

    @PatchMapping("/update/desc")
    @Operation(summary = "Endpoint for updating Message description given an id and new description")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "If Message was successfully updated"),
            ApiResponse(responseCode = "400", description = "If param is missing or invalid"),
            ApiResponse(responseCode = "404" , description = "If no Message was found with given id"),
            ApiResponse(responseCode = "406" , description = "If description was null")
        ]
    )
    fun changeDescription( @RequestParam id : Long , @RequestBody description : String? ): ResponseEntity<Unit> {
        return if (description == null) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        else messageService.changeDescription(id, description)
    }

}