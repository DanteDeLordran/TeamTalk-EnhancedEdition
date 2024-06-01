package dev.darsaras.teamtalk.domain.models.user

import dev.darsaras.teamtalk.domain.models.group.Group
import dev.darsaras.teamtalk.domain.models.role.Role
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    var name : String,
    var lastname : String,
    var username : String,
    var email : String,
    var password : String,
    @ManyToOne
    var role : Role,
    @ManyToMany(mappedBy = "users")
    var groups : MutableSet<Group>? = mutableSetOf(),
    var createdAt : LocalDateTime
)