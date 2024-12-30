//package com.example.todo_list;
//
//import org.example.controllers.TaskController;
//import org.example.models.Task;
//import org.example.services.TaskService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//class TaskControllerTest {
//
//    @InjectMocks
//    private TaskController taskController;
//
//    @Mock
//    private TaskService taskService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createTask() {
//        Task task = new Task("Test Task");
//        when(taskService.createTask(anyString(), webProject.getId())).thenReturn(task);
//
//        Task createdTask = taskController.createTask(task);
//
//        assertNotNull(createdTask);
//        assertEquals("Test Task", createdTask.getTitle());
//        verify(taskService, times(1)).createTask("Test Task", webProject.getId());
//    }
//
//    @Test
//    void getAllTasks() {
//        List<Task> tasks = Arrays.asList(
//                new Task("Task 1"),
//                new Task("Task 2")
//        );
//        when(taskService.getAllTasks()).thenReturn(tasks);
//
//        List<Task> result = taskController.getAllTasks();
//
//        assertEquals(2, result.size());
//        assertEquals("Task 1", result.get(0).getTitle());
//    }
//
//    @Test
//    void getTaskById() {
//        Task task = new Task("Task 1");
//        when(taskService.getTaskById(1)).thenReturn(Optional.of(task));
//
//        Task result = taskController.getTaskById(1);
//
//        assertNotNull(result);
//        assertEquals("Task 1", result.getTitle());
//    }
//
//    @Test
//    void updateTask() {
//        Task task = new Task("Updated Task");
//        when(taskService.updateTask(eq(1), anyString())).thenReturn(Optional.of(task));
//
//        Task result = taskController.updateTask(1, task);
//
//        assertNotNull(result);
//        assertEquals("Updated Task", result.getTitle());
//        verify(taskService, times(1)).updateTask(1, "Updated Task");
//    }
//
//    @Test
//    void deleteTask() {
//        when(taskService.deleteTask(1)).thenReturn(true);
//
//        String response = taskController.deleteTask(1);
//
//        assertEquals("Task deleted", response);
//        verify(taskService, times(1)).deleteTask(1);
//    }
//}
