package com.todo.repository;

import com.mongodb.*;
import com.todo.domain.TodoItem;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;

/**
 * Created by smehta on 7/29/14.
 */
public class TodoItemMongodbRepository implements ItemRepository {


    private static final String MONGO_HOST = System.getenv("MONGO_HOST");
    private static final int MONGO_PORT = Integer.parseInt(System.getenv("MONGO_PORT"));
    private static final String ITEMS_COLLECTION = "todoItems";

    private final Logger log = LoggerFactory.getLogger(TodoItemMongodbRepository.class);

    private DB db;



    public TodoItemMongodbRepository() {

        try {

            MongoClient client = new MongoClient(MONGO_HOST, MONGO_PORT);
            db = client.getDB("todoDB");

            if (db == null) {
                throw  new WebApplicationException("Database is down", 500);
            }
        } catch (UnknownHostException exception) {

            log.error("Error connecting to mongodb server", exception);

            throw  new WebApplicationException("Database is down", 500);
        }
    }

    @Override
    public TodoItem save(TodoItem item) {

        DBCollection collection = db.getCollection(ITEMS_COLLECTION);
        BasicDBObject dbObject = new BasicDBObject
                ("title", item.getTitle())
                .append("body", item.getBody())
                .append("done", item.isDone());

        if (item.getId() != null) {
            dbObject.append("_id", new ObjectId(item.getId()));
        }

        collection.save(dbObject, WriteConcern.NORMAL);
        ObjectId id = (ObjectId)dbObject.get( "_id" );
        item.setId(id.toString());
        return item;
    }

    @Override
    public TodoItem update(TodoItem item) {
        return save(item);
    }

    @Override
    public List<TodoItem> findAll() {

        List<TodoItem> allItems = new ArrayList<>();

        DBCollection collection = db.getCollection(ITEMS_COLLECTION);
        DBCursor cursor = collection.find();

        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            TodoItem item = buildToDoItem(dbObject);
            allItems.add(item);
        }

        return allItems;
    }

    @Override
    public TodoItem findById(String itemId) {

        ObjectId id = new ObjectId(itemId);

        BasicDBObject dbObject = new BasicDBObject("_id", id);
        DBCollection collection = db.getCollection(ITEMS_COLLECTION);
        DBCursor cursor = collection.find(dbObject);
        DBObject resultObject = null;
        if (cursor.hasNext()) {
            resultObject = cursor.next();
        }

        return buildToDoItem(resultObject);
    }

    @Override
    public void delete(String id) {

        BasicDBObject dbObject = new BasicDBObject("_id", new ObjectId(id));
        DBCollection collection = db.getCollection(ITEMS_COLLECTION);
        collection.remove(dbObject);
    }

    private TodoItem buildToDoItem(DBObject dbObject) {

        if(dbObject == null) {
            return null;
        }

        String id = ((ObjectId)dbObject.get("_id")).toString();
        String title = (String)dbObject.get("title");
        String body = (String)dbObject.get("body");
        boolean done = (boolean)dbObject.get("done");

        TodoItem item = new TodoItem();
        item.setId(id);
        item.setTitle(title);
        item.setBody(body);
        item.setDone(done);

        return item;
    }
}
