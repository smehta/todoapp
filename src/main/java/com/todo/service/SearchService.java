package com.todo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.domain.TodoItem;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.Update;
import io.searchbox.indices.CreateIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smehta on 7/26/14.
 */
public class SearchService {

    private static JestClient client;

    private static final SearchService searchService = new SearchService();

    private static final String SEARCH_URI = System.getenv("SEARCHLY_URI");

    private static final String INDEX_NAME = "todoItems";
    private static final String INDEX_TYPE = "items";

    private Logger log = LoggerFactory.getLogger(SearchService.class);

    static  {
        // Configuration
        ClientConfig clientConfig = new ClientConfig.Builder(SEARCH_URI).multiThreaded(true).build();

        // Construct a new Jest client according to configuration via factory
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(SEARCH_URI)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
        try {

            client.execute(new CreateIndex.Builder("todoItems").build());

        } catch (Exception e) {

        }

    }

    public static SearchService getService() {
        return searchService;
    }

    /**
     * Add the given item into search index.
     * @param item
     */
    public void addItemsToIndex(TodoItem item) {

        try {

            Index index = new Index.Builder(item).index(INDEX_NAME).type(INDEX_TYPE).build();
            client.execute(index);

        } catch (Exception e) {
            log.info("Error while adding items to index", e);
        }
    }

    /**
     * remove the given itemId from the search index.
     * @param itemId
     */
    public void removeItemsFromIndex(String itemId) {

        try {
            Delete delete = new Delete.Builder(itemId).index(INDEX_NAME).type(INDEX_TYPE).build();
            client.execute(delete);
        } catch (Exception e) {
            log.error("Error while delete item from index", e);
        }
    }

    /**
     * Updates the item in index.
     * @param item
     */
    public void updateItemsFromIndex(TodoItem item) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String mergedTodoJson = mapper.writeValueAsString(item);

            String script = "{\n" +
                    "    \"doc_as_upsert\" : \"true\",\n" +
                    "    \"doc\" : " + mergedTodoJson +
                    "}";

            Update update =  new Update.Builder(script).index(INDEX_NAME).type(INDEX_TYPE).id(item.getId())
                    .build();
            this.client.execute(update);
        } catch (Exception e) {
            log.error("Error while updating item from index", e);
        }
    }

    /**
     * searches the index with the given query string and returns the list of matched todo items.
     * @param query
     * @return List<TodoItem>
     */
    public List<TodoItem> searchTodoItems(String query) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //searchSourceBuilder.query(QueryBuilders.queryString(query));
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "title^5", "body"));
        Search search = (Search) new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(INDEX_NAME)
                .addType(INDEX_TYPE)
                .build();
        try {

            JestResult result = client.execute(search);
            List<TodoItem> matchedItems = result.getSourceAsObjectList(TodoItem.class);

            log.info("Here are the matched items", matchedItems);

            return matchedItems;

        } catch (Exception e) {
            log.error("Error while searching for items", e);
        }

        return  new ArrayList<>();
    }
}

