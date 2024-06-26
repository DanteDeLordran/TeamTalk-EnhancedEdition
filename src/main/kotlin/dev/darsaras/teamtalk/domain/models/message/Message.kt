package dev.darsaras.teamtalk.domain.models.message

import dev.darsaras.teamtalk.domain.models.channel.Channel
import dev.darsaras.teamtalk.domain.models.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @ManyToOne
    val user : User,
    var description : String,
    @ManyToOne
    val channel : Channel,
    var createdAt : LocalDateTime
)