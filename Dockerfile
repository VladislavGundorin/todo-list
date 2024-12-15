# Используем базовый образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки Maven (JAR-файл)
COPY target/todo-list-1.0-SNAPSHOT.jar app.jar

# Открываем порт, на котором запускается Spring Boot приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
