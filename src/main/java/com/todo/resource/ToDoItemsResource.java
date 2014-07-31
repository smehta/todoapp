package com.todo.resource;

import com.todo.domain.TodoItem;
import com.todo.resource.json.TodoItemRequest;
import com.todo.resource.json.TodoItemResponse;
import com.todo.service.TodoItemService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Represents the todoItem resource.  Currently it supports all CRUD operations.
 */
@Path("/todo/items")
public class ToDoItemsResource {

    private final TodoItemService service = TodoItemService.getService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems(@QueryParam("q")String query) {

        List<TodoItem> items =  service.searchItems(query);
        return Response.ok().entity(new TodoItemResponse(items)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") String itemId) {

        TodoItem item =  service.getItemById(itemId);
        return Response.ok().entity(new TodoItemResponse(item)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@Valid TodoItemRequest todoItemRequest) {

        TodoItem item = service.createItem(todoItemRequest.getTitle(), todoItemRequest.getBody(),
                todoItemRequest.isDone());

        URI createdURI = URI.create("/todo/items/" + item.getId());
        return Response.created(createdURI).entity(new TodoItemResponse(item)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response deleteItem(@PathParam("id") String itemId) {

        service.deleteItem(itemId);

        return Response.ok().build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id")String id, @Valid TodoItemRequest todoItemRequest) {

        TodoItem item = service.updateItem(id, todoItemRequest.getTitle(), todoItemRequest.getBody(),
                todoItemRequest.isDone());

        URI createdURI = URI.create("/todo/items/" + item.getId());
        return Response.created(createdURI).entity(new TodoItemResponse(item)).build();
    }

}
