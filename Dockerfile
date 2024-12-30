FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/todo-list-0.0.1-SNAPSHOT.jar todo-list.jar
EXPOSE 8081
ENV SPRING_APPLICATION_NAME=todo-list
ENV SERVER_PORT=8081
ENTRYPOINT ["java", "-jar", "todo-list.jar"]
