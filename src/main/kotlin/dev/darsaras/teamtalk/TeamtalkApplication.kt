package dev.darsaras.teamtalk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeamtalkApplication

fun main(args: Array<String>) {
	runApplication<TeamtalkApplication>(*args)
}
