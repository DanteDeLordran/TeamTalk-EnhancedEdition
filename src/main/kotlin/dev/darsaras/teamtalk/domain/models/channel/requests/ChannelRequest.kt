package dev.darsaras.teamtalk.domain.models.channel.requests

import dev.darsaras.teamtalk.domain.models.group.Group

data class ChannelRequest(
    val name : String,
    val group : Group
)
