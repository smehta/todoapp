package com.todo.resource.json;

import com.todo.validation.ValidateTodoItem;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by smehta on 7/26/14.
 */

@ValidateTodoItem
public class TodoItemRequest {

    private String title;
    private String body;
    private boolean done;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
