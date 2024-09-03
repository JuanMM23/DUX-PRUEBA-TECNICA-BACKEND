FROM openjdk:17-jdk-slim
COPY target/equipos-0.0.1-SNAPSHOT.jar app_equipos.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_equipos.jar"]
