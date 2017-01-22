package imran.resource;

import imran.domain.Task;
import imran.service.TasksService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static javax.ws.rs.core.HttpHeaders.LOCATION;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TasksResource {

    private final TasksService tasksService;

    @Inject
    public TasksResource(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GET
    public Response getAllTasks() {
        return Response.status(Response.Status.OK).entity(tasksService.getAllTasksInPriorityOrder()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(Task task, @Context UriInfo info) {
        Long id = tasksService.addTask(task);
        URI location = info.getBaseUriBuilder().path("tasks/"+ id).build();
        return Response.status(Response.Status.CREATED).header(LOCATION, location).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(Task task) {
        tasksService.reprioritiseTask(task);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long taskId) {
        tasksService.completeTask(taskId);
        return Response.status(Response.Status.OK).build();
    }
}
