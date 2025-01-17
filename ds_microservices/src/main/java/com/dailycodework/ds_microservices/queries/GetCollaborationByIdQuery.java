package com.dailycodework.ds_microservices.queries;

public class GetCollaborationByIdQuery {
    private final String collaborationId;

    public GetCollaborationByIdQuery(String collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getCollaborationId() {
        return collaborationId;
    }
}
