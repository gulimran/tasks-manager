package imran.resource;

import com.sun.jersey.api.client.ClientResponse;
import imran.domain.Task;
import imran.domain.Tasks;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;

public class TasksResourceTest extends ResourceITest {

    @Before
    public void setup() {
        dataSource().taskTable().clear();
    }

    @Test
    public void testGetAllTasks_ReturnsEmptyList() throws Exception {
        ClientResponse resp = service.path("tasks-service").path("tasks")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        Tasks tasks = resp.getEntity(Tasks.class);

        assertThat(resp.getStatus(), is(200));
        assertThat(tasks.getTasks(), is(empty()));
    }

    @Test
    public void testGetAllTasks_ReturnsTasksList() throws Exception {
        Task task = Task.builder().id(1L).description("test description").priority(1).build();
        dataSource().taskTable().put(task.getId(), task);

        ClientResponse resp = service.path("tasks-service").path("tasks")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        Tasks tasks = resp.getEntity(Tasks.class);

        assertThat(resp.getStatus(), is(200));
        assertThat(tasks.getTasks(), contains(task));
    }

    @Test
    public void testAddTask_ReturnsTaskLocation() throws Exception {
        Task task = Task.builder().id(1L).description("test description").priority(1).build();

        ClientResponse resp = service.path("tasks-service").path("tasks")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, task);

        String locationUrl = resp.getHeaders().getFirst(LOCATION);

        assertThat(resp.getStatus(), is(201));
        assertThat(locationUrl, endsWith("tasks-service/tasks/1"));
        assertThat(dataSource().taskTable().get(task.getId()), is(task));
    }

    @Test
    public void testUpdateTask_UpdatesTaskPriority() throws Exception {
        Task task = Task.builder().id(1L).description("test description").priority(1).build();
        dataSource().taskTable().put(task.getId(), task);

        task.setPriority(2);

        ClientResponse resp = service.path("tasks-service").path("tasks")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, task);

        assertThat(resp.getStatus(), is(200));
        assertThat(dataSource().taskTable().get(task.getId()).getPriority(), is(2));
    }

    @Test
    public void testDeleteTask_RemovesExistingTask() throws Exception {
        Task task = Task.builder().id(1L).description("test description").priority(1).build();
        dataSource().taskTable().put(task.getId(), task);

        ClientResponse resp = service.path("tasks-service").path("tasks")
                .path(task.getId().toString())
                .delete(ClientResponse.class);

        assertThat(resp.getStatus(), is(200));
        assertThat(dataSource().taskTable().get(task.getId()), is(nullValue()));
    }
}