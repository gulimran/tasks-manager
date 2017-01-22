package imran.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks/delete")
public class DeleteTaskController extends BaseController {

    @RequestMapping(value = "/{taskId}", method = RequestMethod.POST)
    public ModelAndView deleteTodo(@PathVariable long taskId) {
        ModelAndView modelAndView = new ModelAndView();
        restTemplate.delete("http://"+hostname+":"+port+"/tasks-service/tasks/"+taskId);
        modelAndView.setViewName("redirect:tasks-list");
        return modelAndView;
    }
}
