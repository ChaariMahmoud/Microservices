package com.dailycodework.ds_microservices.handlers;


import com.dailycodework.ds_microservices.events.CollaborationCreatedEvent;
import com.dailycodework.ds_microservices.models.CollaborationView;
import com.dailycodework.ds_microservices.repositories.CollaborationViewRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class CollaborationEventHandler {

    private final CollaborationViewRepository repository;

    public CollaborationEventHandler(CollaborationViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CollaborationCreatedEvent event) {
        CollaborationView view = new CollaborationView(
                event.getCollaborationId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate()
        );
        repository.save(view);
    }
}

