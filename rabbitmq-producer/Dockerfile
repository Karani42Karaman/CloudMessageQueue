FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests  # Testleri atla

FROM openjdk:17-jdk-slim
WORKDIR rabbitmq-producer
COPY --from=build target/*.jar rabbitmq-producer.jar
ENTRYPOINT ["java", "-jar", "rabbitmq-producer.jar"]