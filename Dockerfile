FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix ./mvnw
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21.0.4_7-jre-jammy AS prod

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]