package site.hayond.revolution.http.problem;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpStatus;

import java.net.URI;
import java.util.Map;

@Introspected
public class Result<T> {

    private static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;

    private Integer status = HTTP_STATUS_OK.getCode();
    private String title = HTTP_STATUS_OK.getReason();
    private String detail;
    private T data;
    private URI type;
    private URI instance;
    private Map<String, Object> parameters;

    public Result() {}

    public Result(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public URI getType() {
        return type;
    }

    public void setType(URI type) {
        this.type = type;
    }

    public URI getInstance() {
        return instance;
    }

    public void setInstance(URI instance) {
        this.instance = instance;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public static <T> Result<T> valueOf(T data) {
        return new Result<>(data);
    }

    public static Result<Void> valueOfTitle(String title) {
        Result<Void> result = new Result<>();
        result.setTitle(title);
        return result;
    }

    public static Result<Void> valueOfDetail(String detail) {
        Result<Void> result = new Result<>();
        result.setDetail(detail);
        return result;
    }

}
