package imran.tasks.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class DeleteTaskControllerTest extends ControllerIntegrationTest {


    @Test
    public void deleteTask_RedirectsToTaskList() throws Exception {
        Long taskId = System.currentTimeMillis();
        this.mvc.perform(post("/tasks/add").accept(MediaType.TEXT_PLAIN)
                .param("id", taskId.toString())
                .param("description", "test description")
                .param("priority", "1"));

        this.mvc.perform(post("/tasks/delete/"+taskId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:tasks-list"));
    }

}