package dev.darsaras.teamtalk.domain.repositories.message

import dev.darsaras.teamtalk.domain.models.message.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long>{
}