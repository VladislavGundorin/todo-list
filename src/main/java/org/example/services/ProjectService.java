package org.example.services;

import org.example.models.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private List<Project> projects = new ArrayList<>();
    private int currentId = 1;

    public Project createProject(String name, String description) {
        Project project = new Project(name, description);
        project.setId(currentId++);
        projects.add(project);
        return project;
    }

    public List<Project> getAllProjects() {
        return projects;
    }

    public Optional<Project> getProjectById(int id) {
        return projects.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Project> updateProject(int id, String name, String description) {
        for (Project project : projects) {
            if (project.getId() == id) {
                project.setName(name);
                project.setDescription(description);
                return Optional.of(project);
            }
        }
        return Optional.empty();
    }

    public boolean deleteProject(int id) {
        return projects.removeIf(project -> project.getId() == id);
    }
}
