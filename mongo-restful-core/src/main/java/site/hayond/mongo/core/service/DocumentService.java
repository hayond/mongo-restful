package site.hayond.mongo.core.service;

import jakarta.inject.Inject;
import org.bson.Document;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class DocumentService {

    @Inject
    private MongoService mongoService;

    public Publisher<String> insert(String databaseName, String collectionName, Document document) {
        return mongoService.existsByCollectionName(databaseName, collectionName,
                mongoCollection -> Mono.from(mongoCollection.insertOne(document)).map(
                        result -> Objects.requireNonNull(result.getInsertedId()).asString().getValue()));
    }

//    public Publisher<String> insert(String databaseName, String collectionName, List<Document> documents) {
//        return mongoService.existsByCollectionName(databaseName, collectionName,
//                mongoCollection -> Mono.from(mongoCollection.insertMany(documents)).map(
//                        result -> result.getInsertedIds()));
//    }

}
