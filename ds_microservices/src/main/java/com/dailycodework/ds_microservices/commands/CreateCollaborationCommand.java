package com.dailycodework.ds_microservices.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.time.LocalDate;

public class CreateCollaborationCommand {
    @TargetAggregateIdentifier
    private final String collaborationId;
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public CreateCollaborationCommand(String collaborationId, String title, String description, LocalDate startDate, LocalDate endDate) {
        this.collaborationId = collaborationId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getCollaborationId() {
        return collaborationId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
