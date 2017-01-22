package imran.service;

import imran.domain.Task;
import imran.domain.Tasks;

public interface TasksService {

    Tasks getAllTasksInPriorityOrder();

    Long addTask(Task task);

    void reprioritiseTask(Task task);

    void completeTask(Long taskId);
}
