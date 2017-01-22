package imran.dao;

import imran.database.DataSource;
import imran.domain.Task;
import imran.exception.DuplicateTaskException;
import imran.exception.TaskNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Map;

import static java.lang.String.format;

@Singleton
public class TasksDaoInMemory implements TasksDao {

    private DataSource dataSource;

    private Map<Long, Task> taskTable;

    @Inject
    public TasksDaoInMemory(DataSource dataSource) {
        this.dataSource = dataSource;
        this.taskTable = dataSource.taskTable();
    }

    public Collection<Task> findAll() {
        return taskTable.values();
    }

    public void save(Task task) {
        if (taskTable.containsKey(task.getId())) {
            throw new DuplicateTaskException(format("Task with id %s already exists", task.getId()));
        }
        taskTable.put(task.getId(), task);
    }

    public void update(Task task) {
        if (!taskTable.containsKey(task.getId())) {
            throw new TaskNotFoundException(format("Task with id %s does not exists", task.getId()));
        }
        taskTable.put(task.getId(), task);
    }

    public void delete(Long taskId) {
        if (!taskTable.containsKey(taskId)) {
            throw new TaskNotFoundException(format("Task with id %s does not exists", taskId));
        }
        taskTable.remove(taskId);
    }
}
