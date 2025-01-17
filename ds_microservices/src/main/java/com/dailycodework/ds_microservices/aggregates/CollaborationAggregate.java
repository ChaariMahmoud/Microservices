package com.dailycodework.ds_microservices.aggregates;

import com.dailycodework.ds_microservices.commands.CreateCollaborationCommand;
import com.dailycodework.ds_microservices.events.CollaborationCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class CollaborationAggregate {

    @AggregateIdentifier
    private String collaborationId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public CollaborationAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CollaborationAggregate(CreateCollaborationCommand command) {
        // Validate command (business logic)
        if (command.getTitle() == null || command.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        // Apply the event
        apply(new CollaborationCreatedEvent(
                command.getCollaborationId(),
                command.getTitle(),
                command.getDescription(),
                command.getStartDate(),
                command.getEndDate()
        ));
    }

    @EventSourcingHandler
    public void on(CollaborationCreatedEvent event) {
        // Update state from the event
        this.collaborationId = event.getCollaborationId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
    }
}
