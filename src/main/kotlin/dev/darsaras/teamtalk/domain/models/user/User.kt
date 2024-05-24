package dev.darsaras.teamtalk.domain.models.user

import dev.darsaras.teamtalk.domain.models.role.Role
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    var name : String,
    var lastname : String,
    var username : String,
    var email : String,
    var password : String,
    @ManyToOne
    var role : Role,
    var createdAt : LocalDateTime
)