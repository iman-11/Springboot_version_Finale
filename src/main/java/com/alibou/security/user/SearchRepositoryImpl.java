package com.alibou.security.user;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements searchrepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRepositoryImpl.class);

    @Autowired
    private MongoClient client;

    @Autowired
    private MongoConverter converter;

    @Override
    public List<User> findByText(String text) {

        final List<User> users = new ArrayList<>();

        try {
            MongoDatabase database = client.getDatabase("jwt-security");
            MongoCollection<Document> collection = database.getCollection("user");

            AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                    new Document("$search",
                            new Document("text",
                                    new Document("query", text)
                                            .append("path", Arrays.asList("lastname", "email","firstname","speciality"))

                                            .append("fuzzy", new Document("maxEdits", 2))
                            )),
                    new Document("$limit", 5L)
            ));

            result.forEach(doc -> {
                try {
                    users.add(converter.read(User.class, doc));
                } catch (Exception e) {
                    // Log or handle the exception
                    LOGGER.error("Error converting document to User: {}", e.getMessage());
                }
            });
        } catch (Exception ex) {
            LOGGER.error("Error executing search query: {}", ex.getMessage());
        }

        return users;
    }
}
