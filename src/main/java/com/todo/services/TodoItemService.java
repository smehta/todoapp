package com.todo.services;


import com.todo.domain.TodoItem;
import com.todo.resources.json.TodoItemJson;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by smehta on 7/26/14.
 */
public class TodoItemService {

    private static final AtomicLong itemCounter = new AtomicLong(0);
    private static final Map<String, TodoItem> items = new HashMap<>();

    private static final TodoItemService service = new TodoItemService();

    public static TodoItemService getService() {
        return service;
    }

    public TodoItem createItem(String title, String body, boolean done) {

        TodoItem item = new TodoItem();
        item.setId(String.valueOf(itemCounter.incrementAndGet()));
        item.setTitle(title);
        item.setBody(body);
        item.setDone(done);

        items.put(item.getId(), item);

        return item;
    }

    public TodoItem getItemById(String id) {
        return items.get(id);
    }

    public List<TodoItem> getAllItems() {

        List<TodoItem> allItems = new ArrayList<>();

        for (String key : items.keySet()) {
            allItems.add(items.get(key));
        }

        return allItems;
    }

    public List<TodoItem> searchItems(String query) {

        List<TodoItem> allItems = new ArrayList<>();

        for (String key : items.keySet()) {
            allItems.add(items.get(key));
        }

        return allItems;
    }

    public TodoItem deleteItem(String id) {
        return items.remove(id);
    }

    public TodoItem updateItem(String id, String title, String body, boolean done) {

        TodoItem currentItem = items.get(id);

        if(currentItem == null) {
            return  null;
        }
        
        currentItem.setTitle(title);
        currentItem.setBody(body);
        currentItem.setDone(done);

        items.put(id, currentItem);

        //if done , then send sms
        return  currentItem;
    }
}
