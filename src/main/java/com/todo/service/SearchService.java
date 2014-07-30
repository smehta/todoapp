package com.todo.service;

import com.todo.domain.TodoItem;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
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

    private static final String SEARCH_URI = "https://site:23c35cdb08adb7481065ed3df478622a@balin-eu-west-1.searchly.com";

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


    public void addItemsToIndex(TodoItem item) {

        try {

            Index index = new Index.Builder(item).index("todoItems").type("item").build();
            client.execute(index);

        } catch (Exception e) {
            log.info("Error while adding items to index", e);
        }
    }

    public List<TodoItem> searchTodoItems(String query) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //searchSourceBuilder.query(QueryBuilders.queryString(query));
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "title^5", "body"));
        Search search = (Search) new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("todoItems")
                .addType("item")
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

