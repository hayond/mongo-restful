package site.hayond.mongo.core.service;

import com.mongodb.MongoNamespace;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Inject;
import org.bson.Document;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import site.hayond.revolution.http.annotation.Service;
import site.hayond.revolution.http.problem.Problem;

import java.util.List;
import java.util.function.Function;

@Service
public class MongoService {

    private static final String DATABASE_EXISTS = "Database Exists";
    private static final String DATABASE_NOT_EXIST = "Database Not Exist";
    private static final String NON_COLLECTION = "Non Collection";
    private static final String COLLECTION_EXISTS = "Collection Exists";
    private static final String COLLECTION_NOT_EXIST = "Collection Not Exist";

    @Inject
    private MongoClient mongoClient;

    public Publisher<List<Document>> listDatabases() {
        return Flux.from(mongoClient.listDatabases()).collectList();
    }

    public Publisher<List<String>> listDatabaseNames() {
        return Flux.from(mongoClient.listDatabaseNames()).collectList();
    }

    public Publisher<Boolean> createDatabase(String databaseName) {
        return Mono.from(listDatabaseNames()).map(databaseNames -> {
            if (!databaseNames.contains(databaseName)) throw Problem.notFound(DATABASE_EXISTS);
            return mongoClient.getDatabase(databaseName);
        }).thenReturn(true);
    }

    public Publisher<Boolean> dropDatabase(String databaseName) {
        return Mono.from(getDatabase(databaseName)).flatMap(
                mongoDatabase -> Mono.from(mongoDatabase.drop())).thenReturn(Boolean.TRUE);
    }

    public Publisher<List<Document>> listCollections(String databaseName) {
        return Mono.from(getDatabase(databaseName)).flatMapMany(MongoDatabase::listCollections).collectList();
    }

    public Publisher<List<String>> listCollectionNames(String databaseName) {
        return Mono.from(getDatabase(databaseName)).flatMapMany(MongoDatabase::listCollectionNames).collectList();
    }

    public Publisher<Boolean> createCollection(String databaseName, String collectionName) {
        return Mono.from(getDatabase(databaseName)).flatMap(
                mongoDatabase -> Flux.from(mongoDatabase.listCollectionNames()).collectList().flatMap(
                        collectionNames -> {
                            if (!StringUtils.hasText(collectionName)) throw Problem.badRequest(NON_COLLECTION);
                            if (collectionNames.contains(collectionName)) throw Problem.badRequest(COLLECTION_EXISTS);
                            return Mono.from(mongoDatabase.createCollection(collectionName));
                        })).thenReturn(Boolean.TRUE);
    }

    public Publisher<Boolean> renameCollection(String databaseName, String collectionName, String newCollectionName) {
        return Mono.from(existsByCollectionName(databaseName, collectionName,
                mongoCollection -> mongoCollection.renameCollection(
                        new MongoNamespace(databaseName, newCollectionName)))).thenReturn(Boolean.TRUE);
    }

    public Publisher<Boolean> dropCollection(String databaseName, String collectionName) {
        return Mono.from(existsByCollectionName(databaseName, collectionName, MongoCollection::drop)).thenReturn(
                Boolean.TRUE);
    }

    public <R> Publisher<R> existsByCollectionName(String databaseName, String collectionName,
                                                   Function<MongoCollection<Document>, Publisher<R>> function) {
        return Mono.from(getDatabase(databaseName)).flatMap(
                mongoDatabase -> Flux.from(mongoDatabase.listCollectionNames()).collectList().flatMap(
                        collectionNames -> {
                            if (!collectionNames.contains(collectionName))
                                throw Problem.notFound(COLLECTION_NOT_EXIST);
                            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
                            return Mono.from(function.apply(mongoCollection));
                        }));
    }

    private Publisher<MongoDatabase> getDatabase(String databaseName) {
        return Mono.from(listDatabaseNames()).map(databaseNames -> {
            if (!databaseNames.contains(databaseName)) throw Problem.notFound(DATABASE_NOT_EXIST);
            return mongoClient.getDatabase(databaseName);
        });
    }

}
