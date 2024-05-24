package dev.darsaras.teamtalk.domain.models.channel

import dev.darsaras.teamtalk.domain.models.group.Group
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "channels")
class Channel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    var name : String,
    @ManyToOne
    var group : Group,
    val createdAt : LocalDateTime
)