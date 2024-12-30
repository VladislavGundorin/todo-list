package org.example;

import org.example.models.Project;
import org.example.services.ProjectService;
import org.example.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) {
        Project project1 = projectService.createProject("Web Development");
        Project project2 = projectService.createProject("Mobile App");

        taskService.createTask("Design database schema", project1.getId());
        taskService.createTask("Create REST API", project1.getId());
        taskService.createTask("Implement user authentication", project1.getId());

        taskService.createTask("Design UI/UX", project2.getId());
        taskService.createTask("Write unit tests", project2.getId());

        System.out.println("Data initialization completed!");
    }
}
