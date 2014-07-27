package com.todo.resources.json;

import com.todo.domain.TodoItem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by smehta on 7/26/14.
 */
public class TodoItemJson {

    private String id;
    private String title;
    private String body;
    private boolean done;



    public TodoItemJson(String id, String title, String body, boolean done) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
    }

    public TodoItemJson(TodoItem item) {

        if(item != null) {
            this.id = item.getId();
            this.title = item.getTitle();
            this.body = item.getBody();
            this.done = item.isDone();
        }
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
