FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/equipos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_equipos.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_equipos.jar"]