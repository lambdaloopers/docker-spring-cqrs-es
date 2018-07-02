package com.app.common.application.http.response;

public class CreateResponse {

    private String entityId;

    public CreateResponse() {
        super();
    }

    public CreateResponse(String entityId) {
        this.setEntityId(entityId);
    }

    public String getEntityId() {
        return this.entityId;
    }

    private void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
