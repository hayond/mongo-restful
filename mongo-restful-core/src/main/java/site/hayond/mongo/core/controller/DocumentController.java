package site.hayond.mongo.core.controller;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.bson.Document;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import site.hayond.mongo.core.service.DocumentService;
import site.hayond.revolution.http.problem.Result;

import java.io.IOException;

@Tag(name = "Document")
@Controller("/{databaseName}/{collectionName}/document")
public class DocumentController {

    @Inject
    private DocumentService documentService;
    @Inject
    private JsonMapper jsonMapper;

//    @Get
//    @SingleResult
//    public Publisher<Result<List<Document>>> get(String databaseName) {
//        return Mono.from(mongoService.listCollections(databaseName)).map(Result::valueOf);
//    }

    @Put
    @SingleResult
    public Publisher<Result<Boolean>> put(String databaseName, String collectionName,
                                          @Schema(example = "{}") @Body JsonNode data) {
        try {
            Document document = jsonMapper.readValueFromTree(data, Document.class);
            System.out.println(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        documentService.insert(databaseName, collectionName);
//        BasicDBObject basicDBObject = BasicDBObject.parse(data);
//        Document document = Document.parse(data);
        return Mono.just(Result.valueOf(Boolean.TRUE));
    }

//    @Patch("{_uid}")
//    @SingleResult
//    @Valid
//    public Publisher<Result<Boolean>> patch(String databaseName, String collectionName, @NotBlank String name) {
//        return Mono.from(mongoService.renameCollection(databaseName, collectionName, name)).map(Result::valueOf);
//    }
//
//    @Delete("{_uid}")
//    @SingleResult
//    public Publisher<Result<Boolean>> delete(String databaseName, String collectionName) {
//        return Mono.from(mongoService.dropCollection(databaseName, collectionName)).map(Result::valueOf);
//    }

}
