package imran.tasks.controller;

import imran.tasks.form.TaskForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpHeaders.LOCATION;

@Controller
@RequestMapping("/tasks/add")
public class AddTaskController extends BaseController {

    @ModelAttribute("task")
    public TaskForm createTaskForm() {
        return TaskForm.builder().build();
    }

    @GetMapping
    public String redirectToAddTodoPage(Model model) {
        return "add-task";
    }

    @PostMapping
    public String addTask(TaskForm task, Model model) {
        System.out.println("model = " + model.asMap());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://"+hostname+":"+port+"/tasks-service/tasks", task, String.class);
        model.addAttribute("taskLocation", responseEntity.getHeaders().get(LOCATION).get(0));
        return "tasks-list";
    }
}
