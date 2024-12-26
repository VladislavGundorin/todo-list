FROM openjdk:17
COPY build/libs/todo-list-0.0.1-SNAPSHOT.jar todo-list.jar
ENTRYPOINT ["java", "-jar", "todo-list.jar"]
