package imran.dao;

import imran.domain.Task;

import java.util.Collection;

public interface TasksDao {

    Collection<Task> findAll();
    void save(Task task);
    void update(Task task);
    void delete(Long taskId);
}
