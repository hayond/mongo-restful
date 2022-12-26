package site.hayond.mongo.core.controller;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.bson.Document;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import site.hayond.mongo.core.service.MongoService;
import site.hayond.revolution.http.problem.Result;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Tag(name = "Collection")
@Controller("/{databaseName}/")
public class CollectionController {

    @Inject
    private MongoService mongoService;

    @Get("collection")
    @SingleResult
    public Publisher<Result<List<Document>>> get(String databaseName) {
        return Mono.from(mongoService.listCollections(databaseName)).map(Result::valueOf);
    }

    @Get("collectionNames")
    @SingleResult
    public Publisher<Result<List<String>>> getNames(String databaseName) {
        return Mono.from(mongoService.listCollectionNames(databaseName)).map(Result::valueOf);
    }

    @Put("collection")
    @SingleResult
    @Valid
    public Publisher<Result<Boolean>> put(String databaseName, @NotBlank String name) {
        return Mono.from(mongoService.createCollection(databaseName, name)).map(Result::valueOf);
    }

    @Patch("collection/{collectionName}")
    @SingleResult
    @Valid
    public Publisher<Result<Boolean>> patch(String databaseName, String collectionName, @NotBlank String name) {
        return Mono.from(mongoService.renameCollection(databaseName, collectionName, name)).map(Result::valueOf);
    }

    @Delete("collection/{collectionName}")
    @SingleResult
    public Publisher<Result<Boolean>> delete(String databaseName, String collectionName) {
        return Mono.from(mongoService.dropCollection(databaseName, collectionName)).map(Result::valueOf);
    }

}
