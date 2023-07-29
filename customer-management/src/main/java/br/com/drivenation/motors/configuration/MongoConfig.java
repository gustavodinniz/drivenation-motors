package br.com.drivenation.motors.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.stream.StreamSupport;

@Slf4j
@Startup
@ApplicationScoped
@RequiredArgsConstructor
public class MongoConfig {

    private static final String DOCUMENT_INDEX = "document_1";
    private final MongoClient mongoClient;

    @PostConstruct
    void init() {
        try {
            MongoCollection<Document> collection = mongoClient.getDatabase("customer").getCollection("customers");
            boolean indexExists = StreamSupport.stream(collection.listIndexes().spliterator(), false)
                    .anyMatch(index -> DOCUMENT_INDEX.equals(index.getString("name")));
            if (!indexExists) {
                collection.createIndex(Indexes.ascending("document"), new IndexOptions().unique(true));
                log.info("Created unique index {} on collection {}.", DOCUMENT_INDEX, "customers");
            }
        } catch (Exception e) {
            log.error("Error while creating index", e);
        }
    }
}
