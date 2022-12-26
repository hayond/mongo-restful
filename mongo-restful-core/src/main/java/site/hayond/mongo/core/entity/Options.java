package site.hayond.mongo.core.entity;

public class Options {

    private Boolean bypassDocumentValidation;
    private String comment;

    public Boolean getBypassDocumentValidation() {
        return bypassDocumentValidation;
    }

    public void setBypassDocumentValidation(Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
