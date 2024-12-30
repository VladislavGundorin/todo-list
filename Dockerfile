FROM openjdk:17-jdk-slim

WORKDIR /app

COPY todo-list-1.0-SNAPSHOT.jar app.jar

EXPOSE 8082

ENV SPRING_APPLICATION_NAME=todo-list
ENV SERVER_PORT=8082

ENTRYPOINT ["java", "-jar", "app.jar"]
