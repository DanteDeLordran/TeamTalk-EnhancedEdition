package dev.darsaras.teamtalk.domain.models.group

import dev.darsaras.teamtalk.domain.models.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "groups")
class Group (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    var name : String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_groups",
        joinColumns = [JoinColumn(name= "group_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var users : MutableSet<User>,
    val createdAt : LocalDateTime
)