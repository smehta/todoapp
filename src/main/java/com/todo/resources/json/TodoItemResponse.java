package com.todo.resources.json;

import com.todo.domain.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smehta on 7/26/14.
 */
public class TodoItemResponse {

    private List<TodoItemJson> todoItems = new ArrayList<>();

    public TodoItemResponse(List<TodoItem> items) {
        if(items != null && !items.isEmpty()) {
            for (TodoItem item : items) {
                if (item != null) {
                    this.todoItems.add(new TodoItemJson(item));
                }

            }

        }
    }

    public  TodoItemResponse(TodoItem item) {
        if (item != null) {
            this.todoItems.add(new TodoItemJson(item));
        }
    }

    public List<TodoItemJson> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItemJson> todoItems) {
        this.todoItems = todoItems;
    }
}
