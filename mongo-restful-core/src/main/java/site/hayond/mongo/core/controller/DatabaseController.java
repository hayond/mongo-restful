package site.hayond.mongo.core.controller;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
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

@Tag(name = "Database")
@Controller("/")
public class DatabaseController {

    @Inject
    private MongoService mongoService;

    @Get("database")
    @SingleResult
    public Publisher<Result<List<Document>>> get() {
        return Mono.from(mongoService.listDatabases()).map(Result::valueOf);
    }

    @Get("databaseNames")
    @SingleResult
    public Publisher<Result<List<String>>> getNames() {
        return Mono.from(mongoService.listDatabaseNames()).map(Result::valueOf);
    }

    @Put("database")
    @SingleResult
    @Valid
    public Publisher<Result<Boolean>> put(@NotBlank String name) {
        return Mono.from(mongoService.createDatabase(name)).map(Result::valueOf);
    }

    @Delete("database/{databaseName}")
    @SingleResult
    public Publisher<Result<Boolean>> delete(String databaseName) {
        return Mono.from(mongoService.dropDatabase(databaseName)).map(Result::valueOf);
    }

}
