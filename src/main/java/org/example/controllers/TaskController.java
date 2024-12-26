package org.example.controllers;

import org.example.models.Task;
import org.example.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task.getTitle());
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.orElse(null);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> updatedTask = taskService.updateTask(id, task.getTitle());
        return updatedTask.orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id) ? "Task deleted" : "Task not found";
    }
}
