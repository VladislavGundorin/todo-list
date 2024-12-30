FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8081

ENV SPRING_APPLICATION_NAME=todo-list
ENV SERVER_PORT=8081

ENTRYPOINT ["java", "-jar", "app.jar"]
