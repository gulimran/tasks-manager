package imran.server;

import com.google.inject.AbstractModule;
import imran.dao.TasksDao;
import imran.dao.TasksDaoInMemory;
import imran.database.DataSource;
import imran.service.TasksService;
import imran.service.TasksServiceImpl;

public class TasksModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataSource.class);
        bind(TasksDao.class).to(TasksDaoInMemory.class);
        bind(TasksService.class).to(TasksServiceImpl.class);
    }
}
