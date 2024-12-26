package org.example.services;

import org.example.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int currentId = 1;

    public Task createTask(String title) {
        Task task = new Task(title);
        task.setId(currentId++);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Optional<Task> getTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public Optional<Task> updateTask(int id, String title) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(title);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
