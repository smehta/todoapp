package com.todo.repository;

import com.todo.domain.TodoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by smehta on 7/29/14.
 */
public class TodoItemInMemoryRepository implements ItemRepository {


    private static final AtomicLong itemCounter = new AtomicLong(0);
    private static final Map<String, TodoItem> items = new HashMap<>();

    @Override
    public TodoItem save(TodoItem item) {


        item.setId(String.valueOf(itemCounter.incrementAndGet()));
        items.put(item.getId(), item);

        return item;
    }

    @Override
    public TodoItem update(TodoItem item) {
        return  items.put(item.getId(), item);
    }

    @Override
    public List<TodoItem> findAll() {

        List<TodoItem> allItems = new ArrayList<>();

        for (String key : items.keySet()) {
            allItems.add(items.get(key));
        }

        return allItems;
    }

    @Override
    public TodoItem findById(String id) {
        return items.get(id);
    }

    @Override
    public void delete(String id) {

        items.remove(id);
    }
}
