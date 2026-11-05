package sg.edu.ntu.taskflow_api.controller;

import sg.edu.ntu.taskflow_api.model.Department;
import sg.edu.ntu.taskflow_api.model.Staff;
import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.model.TaskStatus;
import sg.edu.ntu.taskflow_api.service.TaskService;

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
    private TaskService taskService;

    /**
     * Test: GET /tasks should return list of tasks
     */
    @Test
    public void testGetAllTasks() throws Exception {

        // Arrange - Create test data
        Staff mockStaff1 = new Staff();
        mockStaff1.setId(1L);
        mockStaff1.setName("John Doe");
        mockStaff1.setEmail("john.doe@example.com");
        mockStaff1.setDepartment(Department.ENGINEERING);

        Staff mockStaff2 = new Staff();
        mockStaff2.setId(2L);
        mockStaff2.setName("Jane Smith");
        mockStaff2.setEmail("jane.smith@example.com");
        mockStaff2.setDepartment(Department.HR);

        // Arrange - Create test data
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTaskName("Task 1");
        task1.setDescription("Description 1");
        task1.setStatus(TaskStatus.PENDING);
        task1.setStaff(mockStaff1);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTaskName("Task 2");
        task2.setDescription("Description 2");
        task2.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStaff(mockStaff2);

        List<Task> tasks = Arrays.asList(task1, task2);
        List<Staff> staffList = Arrays.asList(mockStaff1, mockStaff2);

        // Mock the service to return test data
        when(taskService.getAllTasks()).thenReturn(tasks);
        when(taskService.getAllStaff()).thenReturn(staffList);

        // Act & Assert - Make request and verify response
        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Task 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].status").value("PENDING"))
                .andExpect(jsonPath("$[1].taskName").value("Task 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].status").value("IN_PROGRESS"));
    }
}