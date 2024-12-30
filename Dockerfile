FROM openjdk:17
COPY build/libs/todo-list-1.0-SNAPSHOT.jar todo-list.jar
ENTRYPOINT ["java", "-jar", "small-api.jar"]
