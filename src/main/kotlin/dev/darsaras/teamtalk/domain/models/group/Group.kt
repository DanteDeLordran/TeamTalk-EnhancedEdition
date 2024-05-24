package dev.darsaras.teamtalk.domain.models.group

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "groups")
class Group (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    var name : String,
    val createdAt : LocalDateTime
)