package dev.darsaras.teamtalk

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
	info = Info(
		title = "TeamTalk docs",
		description = "Documentation for project's endpoints and schemas",
		version = "v1",
		contact = Contact(
			name = "Dante de Lordran",
			email = "dantelopez305@outlook.com",
			url = "https://github.com/DanteDeLordran"
		)
	)
)
@SpringBootApplication
class TeamtalkApplication

fun main(args: Array<String>) {
	runApplication<TeamtalkApplication>(*args)
}
