package site.hayond.mongo.core.entity;

import com.mongodb.BasicDBObject;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class OperationData {

    private String data;
    private Options options;

    public static BasicDBObject parse(String bodyData) {
        return BasicDBObject.parse(bodyData);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

}
