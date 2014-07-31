package com.todo.repository;

import com.todo.domain.TodoItem;

import java.util.List;

/**
 * Created by smehta on 7/29/14.
 */
public interface ItemRepository {


    public TodoItem save(TodoItem item);

    public TodoItem update(TodoItem item);

    public List<TodoItem> findAll();

    public TodoItem findById(String id);

    public void delete(String id);
}
