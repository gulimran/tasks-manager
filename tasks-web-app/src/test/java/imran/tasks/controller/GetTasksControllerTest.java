package imran.tasks.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class GetTasksControllerTest  extends ControllerIntegrationTest {

    @Test
    public void getTasksList_ReturnsEmptyList() throws Exception {
        this.mvc.perform(get("/tasks").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks-list"))
                .andExpect(model().attribute("tasksList", is(nullValue())));
    }
}