package imran.tasks.controller;

import imran.tasks.form.TaskForm;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UpdateTaskController extends BaseController {

    @RequestMapping("/update/{taskId}")
    public String redirectToUpdateTodoPage(@PathVariable long todoId, Model model) {
        ResponseEntity<TaskForm> responseEntity = restTemplate.getForEntity("http://"+hostname+":"+port+"/todo/task/"+todoId, TaskForm.class);
        TaskForm taskForm = responseEntity.getBody();
        model.addAttribute(taskForm);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doUpdateTodo(@ModelAttribute TaskForm task) {
        restTemplate.put("http://"+hostname+":"+port+"/todo/tasks", task, TaskForm.class);
        return "redirect:/tasks";
    }
}
