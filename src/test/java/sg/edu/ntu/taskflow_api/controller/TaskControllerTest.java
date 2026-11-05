package sg.edu.ntu.taskflow_api.controller;

import sg.edu.ntu.taskflow_api.model.Staff;
import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.model.TaskStatus;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for TaskController
 * Tests the GET /tasks endpoint
 */
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskRepository taskRepository;

    /**
     * Test: GET /tasks should return list of tasks
     */
    @Test
    public void testGetAllTasks() throws Exception {

        // Arrange - Create test data
        Staff mockStaff = new Staff();
        mockStaff.setId(1L);
        mockStaff.setName("John Doe");

        // Arrange - Create test data
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTaskName("Task 1");
        task1.setDescription("Description 1");
        task1.setStatus(TaskStatus.PENDING);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTaskName("Task 2");
        task2.setDescription("Description 2");
        task2.setStatus(TaskStatus.IN_PROGRESS);

        List<Task> tasks = Arrays.asList(task1, task2);

        // Mock the repository to return test data
        when(taskRepository.findAll()).thenReturn(tasks);

        // Act & Assert - Make request and verify response
        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].task_name").value("Task 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].status").value("PENDING"))
                .andExpect(jsonPath("$[1].task_name").value("Task 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].status").value("IN_PROGRESS"));
    }
}