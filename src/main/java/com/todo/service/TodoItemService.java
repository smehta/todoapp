package com.todo.service;


import com.todo.domain.TodoItem;
import com.todo.repository.ItemRepository;
import com.todo.repository.TodoItemInMemoryRepository;
import com.todo.repository.TodoItemMongodbRepository;
import exception.ItemNotFoundException;
import io.searchbox.core.Search;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by smehta on 7/26/14.
 */
public class TodoItemService {


    private final Logger log = LoggerFactory.getLogger(TodoItemService.class);

    private static final TodoItemService service = new TodoItemService();

    private final ItemRepository repository = new TodoItemMongodbRepository();

    private final NotificationService notificationService = new TwilioNotificationServiceImpl();

    private final SearchService searchService = SearchService.getService();


    public static TodoItemService getService() {
        return service;
    }

    public TodoItem createItem(String title, String body, boolean done) {

        TodoItem item = new TodoItem();
        item.setTitle(title);
        item.setBody(body);
        item.setDone(done);

        item = repository.save(item);

        searchService.addItemsToIndex(item);

        return item;
    }

    public TodoItem getItemById(String id) {

        TodoItem item = repository.findById(id);
        if (item == null) {
            throw new ItemNotFoundException();
        }
        return item;
    }

    public List<TodoItem> searchItems(String query) {

        if (StringUtils.isEmpty(query)) {
            return  repository.findAll();
        }
        return searchService.searchTodoItems(query);
    }

    public void deleteItem(String id) {

        //check if id is valid
        TodoItem currentItem = getItemById(id);

        repository.delete(id);

        //remove deletedItem from search index;
        searchService.removeItemsFromIndex(id);
    }

    public TodoItem updateItem(String id, String title, String body, boolean markDone) {

        TodoItem currentItem = getItemById(id);

        currentItem.setTitle(title);
        currentItem.setBody(body);
        currentItem.setDone(markDone);

        repository.update(currentItem);

        //update item in index;
        searchService.updateItemsFromIndex(currentItem);

        //if done , then send sms
        if(markDone) {

            boolean sent = notificationService.send(title);

            if(!sent) {
                log.info("Unable to send SMS on completion of task");
            }
        }

        return  currentItem;
    }
}
