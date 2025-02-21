FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests  # Testleri atla

FROM openjdk:17-jdk-slim
WORKDIR rabbitmq-mqtt-consumer
COPY --from=build target/*.jar rabbitmq-mqtt-consumer.jar
ENTRYPOINT ["java", "-jar", "rabbitmq-mqtt-consumer.jar"]