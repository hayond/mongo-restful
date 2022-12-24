package site.hayond.revolution.http.problem;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpStatus;
import io.micronaut.problem.HttpStatusType;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Introspected
public class Problem extends ThrowableProblem {
    private static final StatusType HTTP_STATUS_TYPE_BAD_REQUEST = new HttpStatusType(HttpStatus.BAD_REQUEST);
    private static final StatusType HTTP_STATUS_TYPE_NOT_FOUND = new HttpStatusType(HttpStatus.NOT_FOUND);
    private static final StatusType HTTP_STATUS_TYPE_UNAUTHORIZED = new HttpStatusType(HttpStatus.UNAUTHORIZED);
    private static final StatusType HTTP_STATUS_TYPE_FORBIDDEN = new HttpStatusType(HttpStatus.FORBIDDEN);
    private static final StatusType HTTP_STATUS_TYPE_INTERNAL_SERVER_ERROR = new HttpStatusType(
            HttpStatus.INTERNAL_SERVER_ERROR);

    private final URI type;
    private final String title;
    private final StatusType status;
    private final String detail;
    private final URI instance;
    private final Map<String, Object> parameters;

    public Problem() {
        this(null);
    }

    public Problem(
            @Nullable final URI type) {
        this(type, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title) {
        this(type, title, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final StatusType status) {
        this(type, title, status, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final StatusType status,
            @Nullable final String detail) {
        this(type, title, status, detail, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final StatusType status,
            @Nullable final String detail,
            @Nullable final URI instance) {
        this(type, title, status, detail, instance, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final StatusType status,
            @Nullable final String detail,
            @Nullable final URI instance,
            @Nullable final ThrowableProblem cause) {
        this(type, title, status, detail, instance, cause, null);
    }

    public Problem(
            @Nullable final URI type,
            @Nullable final String title,
            @Nullable final StatusType status,
            @Nullable final String detail,
            @Nullable final URI instance,
            @Nullable final ThrowableProblem cause,
            @Nullable final Map<String, Object> parameters) {
        super(cause);
        this.status = Optional.ofNullable(status).orElse(HTTP_STATUS_TYPE_BAD_REQUEST);
        this.type = type;
        this.title = Optional.ofNullable(title).orElse(this.status.getReasonPhrase());
        this.detail = detail;
        this.instance = instance;
        this.parameters = Optional.ofNullable(parameters).orElseGet(LinkedHashMap::new);
    }

    @Override
    public URI getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public StatusType getStatus() {
        return status;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public URI getInstance() {
        return instance;
    }

    @Override
    public Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public static ThrowableProblem valueOf(@Nullable final HttpStatus httpStatus) {
        StatusType StatusType = httpStatus != null ? new HttpStatusType(httpStatus) : null;
        return new Problem(null, null, StatusType);
    }

    public static ThrowableProblem valueOf(@Nullable final HttpStatus httpStatus, @Nullable final String title) {
        StatusType StatusType = httpStatus != null ? new HttpStatusType(httpStatus) : null;
        return new Problem(null, title, StatusType);
    }

    public static ThrowableProblem valueOf(@Nullable final HttpStatus httpStatus, @Nullable final String title,
                                           @Nullable final String detail) {
        StatusType StatusType = httpStatus != null ? new HttpStatusType(httpStatus) : null;
        return new Problem(null, title, StatusType, detail);
    }

    public static ThrowableProblem badRequest(final String title) {
        return new Problem(null, title);
    }

    public static ThrowableProblem badRequest(final Map<String, Object> parameters) {
        return new Problem(null, null, null, null, null, null, parameters);
    }

    public static ThrowableProblem badRequest(final String title, final Map<String, Object> parameters) {
        return new Problem(null, title, null, null, null, null, parameters);
    }

    public static ThrowableProblem notFound(final String title) {
        return new Problem(null, title, HTTP_STATUS_TYPE_NOT_FOUND);
    }

    public static ThrowableProblem notFound(final Map<String, Object> parameters) {
        return new Problem(null, null, HTTP_STATUS_TYPE_NOT_FOUND, null, null, null, parameters);
    }

    public static ThrowableProblem notFound(final String title, final Map<String, Object> parameters) {
        return new Problem(null, title, HTTP_STATUS_TYPE_NOT_FOUND, null, null, null, parameters);
    }

    public static ThrowableProblem unauthorized(final String title) {
        return new Problem(null, title, HTTP_STATUS_TYPE_UNAUTHORIZED);
    }

    public static ThrowableProblem unauthorized(final Map<String, Object> parameters) {
        return new Problem(null, null, HTTP_STATUS_TYPE_UNAUTHORIZED, null, null, null, parameters);
    }

    public static ThrowableProblem unauthorized(final String title, final Map<String, Object> parameters) {
        return new Problem(null, title, HTTP_STATUS_TYPE_UNAUTHORIZED, null, null, null, parameters);
    }

    public static ThrowableProblem forbidden(final String title) {
        return new Problem(null, title, HTTP_STATUS_TYPE_FORBIDDEN);
    }

    public static ThrowableProblem forbidden(final Map<String, Object> parameters) {
        return new Problem(null, null, HTTP_STATUS_TYPE_FORBIDDEN, null, null, null, parameters);
    }

    public static ThrowableProblem forbidden(final String title, final Map<String, Object> parameters) {
        return new Problem(null, title, HTTP_STATUS_TYPE_FORBIDDEN, null, null, null, parameters);
    }

    public static ThrowableProblem error(final String title) {
        return new Problem(null, title, HTTP_STATUS_TYPE_INTERNAL_SERVER_ERROR);
    }

    public static ThrowableProblem error(final Map<String, Object> parameters) {
        return new Problem(null, null, HTTP_STATUS_TYPE_INTERNAL_SERVER_ERROR, null, null, null, parameters);
    }

    public static ThrowableProblem error(final String title, final Map<String, Object> parameters) {
        return new Problem(null, title, HTTP_STATUS_TYPE_INTERNAL_SERVER_ERROR, null, null, null, parameters);
    }

    public static ProblemBuilder build() {
        return new ProblemBuilder();
    }

}
