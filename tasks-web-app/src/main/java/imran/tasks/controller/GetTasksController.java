package imran.tasks.controller;

import imran.tasks.form.TasksForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
public class GetTasksController extends BaseController {

    @GetMapping
    @SuppressWarnings("unchecked")
    public ModelAndView getTaskList(Model model) throws Exception {
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://"+hostname+":"+port+"/tasks-service/tasks", String.class);

        TasksForm todoList = objectMapper.readValue(responseEntity.getBody(), TasksForm.class);
        return new ModelAndView("tasks-list", "tasksList", todoList.getTasks());
    }

}
