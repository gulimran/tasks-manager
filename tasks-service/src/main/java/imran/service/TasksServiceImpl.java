package imran.service;

import com.google.inject.Singleton;
import imran.dao.TasksDao;
import imran.domain.Task;
import imran.domain.Tasks;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;

@Singleton
public class TasksServiceImpl implements TasksService {

    @Inject
    private TasksDao taskDao;

    public Tasks getAllTasksInPriorityOrder() {
        ArrayList<Task> tasks = new ArrayList<>(taskDao.findAll());
        Collections.sort(tasks, (task1, task2) -> task1.getPriority() - task2.getPriority());
        return Tasks.builder().tasks(tasks).build();
    }

    public Long addTask(Task task) {
        taskDao.save(task);
        return task.getId();
    }

    public void reprioritiseTask(Task task) {
        taskDao.update(task);
    }

    public void completeTask(Long taskId) {
        taskDao.delete(taskId);
    }
}
