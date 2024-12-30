package org.example.services;

import org.example.models.Task;
import org.example.models.Project;
import org.example.repository.ProjectRepository;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Task createTask(String title, int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));
        Task task = new Task();
        task.setTitle(title);
        task.setProject(project);
        return taskRepository.save(task);
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
