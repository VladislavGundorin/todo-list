package com.example.todo_list;

import org.example.controllers.ProjectController;
import org.example.models.Project;
import org.example.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.*;
        import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProject() {
        Project project = new Project("Test Project", "Description");
        when(projectService.createProject(anyString(), anyString())).thenReturn(project);

        Project createdProject = projectController.createProject(project);

        assertNotNull(createdProject);
        assertEquals("Test Project", createdProject.getName());
        verify(projectService, times(1)).createProject("Test Project", "Description");
    }

    @Test
    void getAllProjects() {
        List<Project> projects = Arrays.asList(
                new Project("Project 1", "Description 1"),
                new Project("Project 2", "Description 2")
        );
        when(projectService.getAllProjects()).thenReturn(projects);

        List<Project> result = projectController.getAllProjects();

        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getName());
    }

    @Test
    void getProjectById() {
        Project project = new Project("Project 1", "Description");
        when(projectService.getProjectById(1)).thenReturn(Optional.of(project));

        Project result = projectController.getProjectById(1);

        assertNotNull(result);
        assertEquals("Project 1", result.getName());
    }

    @Test
    void updateProject() {
        Project project = new Project("Updated Project", "Updated Description");
        when(projectService.updateProject(eq(1), anyString(), anyString())).thenReturn(Optional.of(project));

        Project result = projectController.updateProject(1, project);

        assertNotNull(result);
        assertEquals("Updated Project", result.getName());
        verify(projectService, times(1)).updateProject(1, "Updated Project", "Updated Description");
    }

    @Test
    void deleteProject() {
        when(projectService.deleteProject(1)).thenReturn(true);

        String response = projectController.deleteProject(1);

        assertEquals("Project deleted", response);
        verify(projectService, times(1)).deleteProject(1);
    }
}
