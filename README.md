# TeamTalk Enhanced Edition
TeamTalk but based

## Stack used
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/kotlin/kotlin-original.svg" height=80/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" height=80/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg" height=80/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original-wordmark.svg" height=80 />

## Environment variables
```
ACTIVE_PROFILE
DB_USER
DB_PASSWORD
DB_HOST
DB_PORT
DB_NAME
PG_EMAIL
PG_PASSWORD
```

## Build & run (Google Jib)
Jib handles all the steps of packaging your app into a container image. You don't need to know best practices for creating Dockerfiles or have Docker installed.
```
.\mvnw compile jib:dockerBuild
```

## Build native image (GraalVM)
GraalVM compiles your Java applications ahead of time into standalone binaries that start instantly, provide peak performance with no warmup, and use fewer resources.
```
.\mvnw -Pnative native:compile
```

## Run migrations
This project uses Flyway as database migration and version manager. For creating a new migration you can simply add a new .sql file on the db folder at : **./src/main/resources/db** , when re-running the project , Spring will automatically perform the migration

## Swagger UI documentation
All the project is documented using OpenAPI aka Swagger , you can access the endpoint's and schema's documentation under the url
```
http://localhost:8080/api/swagger-ui/index.html
```