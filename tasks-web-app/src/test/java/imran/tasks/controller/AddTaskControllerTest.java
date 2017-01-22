package imran.tasks.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AddTaskControllerTest extends ControllerIntegrationTest {

    @Test
    public void addTask_CreatesFormObject() throws Exception {
        this.mvc.perform(get("/tasks/add").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("add-task"))
                .andExpect(model().attribute("task", is(notNullValue())));
    }

    @Test
    public void addTask_ReturnsTaskLocationUrl() throws Exception {
        Long taskId = System.currentTimeMillis();
        this.mvc.perform(post("/tasks/add").accept(MediaType.TEXT_PLAIN)
                .param("id", taskId.toString())
                .param("description", "test description")
                .param("priority", "1")
        ).andExpect(status().isOk())
                .andExpect(view().name("tasks-list"))
                .andExpect(model().attribute("task", is(notNullValue())))
                .andExpect(model().attribute("taskLocation", is("http://localhost:8080/tasks-service/tasks/"+taskId)));
    }

}