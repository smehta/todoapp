package com.todo.domain;


/**
 * Created by smehta on 7/26/14.
 */
public class TodoItem {

    private String id;
    private String title;
    private String body;
    private boolean done;

    public TodoItem(String id, String title, String body, boolean done) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
    }

    public TodoItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
