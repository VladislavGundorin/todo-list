package org.example.controllers;

import org.example.models.Project;
import org.example.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project.getName(), project.getDescription());
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable int id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.orElse(null); // Вернём null, если проект не найден
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project project) {
        Optional<Project> updatedProject = projectService.updateProject(id, project.getName(), project.getDescription());
        return updatedProject.orElse(null); // Вернём null, если обновление не удалось
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable int id) {
        return projectService.deleteProject(id) ? "Project deleted" : "Project not found";
    }
}
