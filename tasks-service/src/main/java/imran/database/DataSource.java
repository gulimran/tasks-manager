package imran.database;

import com.google.inject.Singleton;
import imran.domain.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class DataSource {

    private static final Map<Long, Task> TASK_TABLE = new ConcurrentHashMap<>();

    public Map<Long, Task> taskTable() {
        return TASK_TABLE;
    }

}
