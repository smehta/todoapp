package com.todo.resources;

import com.todo.domain.TodoItem;
import com.todo.resources.json.TodoItemRequest;
import com.todo.resources.json.TodoItemResponse;
import com.todo.service.TodoItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/todo/items")
public class ToDoItemsResource {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemResponse getAllItems(@QueryParam("q")String query) {

        List<TodoItem> items =  TodoItemService.getService().searchItems(query);
        return new TodoItemResponse(items);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemResponse getItem(@PathParam("id") String itemId) {

        TodoItem item =  TodoItemService.getService().getItemById(itemId);
        return new TodoItemResponse(item);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemResponse addItem(TodoItemRequest todoItemRequest) {

        TodoItem item = TodoItemService.getService().createItem(todoItemRequest.getTitle(), todoItemRequest.getBody(),
                todoItemRequest.isDone());

        return new TodoItemResponse(item);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  void deleteItem(@PathParam("id") String itemId) {

        TodoItemService.getService().deleteItem(itemId);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemResponse updateItem(@PathParam("id")String id, TodoItemRequest todoItemRequest) {

        TodoItem item = TodoItemService.getService().updateItem(id, todoItemRequest.getTitle(), todoItemRequest.getBody(),
                todoItemRequest.isDone());

        return new TodoItemResponse(item);
    }

}
