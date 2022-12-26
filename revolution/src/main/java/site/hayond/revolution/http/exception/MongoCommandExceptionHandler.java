package site.hayond.revolution.http.exception;

import com.mongodb.MongoCommandException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import site.hayond.revolution.http.problem.Result;

@Produces
@Singleton
@Requires(classes = {MongoCommandException.class, ExceptionHandler.class})
public class MongoCommandExceptionHandler implements ExceptionHandler<MongoCommandException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, MongoCommandException exception) {
        HttpStatus status = Result.HTTP_STATUS_INTERNAL_SERVER_ERROR;
        Result body = Result.valueOf(status.getCode(), exception.getErrorCodeName(), exception.getErrorMessage());
        return HttpResponseFactory.INSTANCE.status(status).body(body);
    }

}
