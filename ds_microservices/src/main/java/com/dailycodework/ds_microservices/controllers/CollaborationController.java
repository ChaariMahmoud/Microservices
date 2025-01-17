/*package com.dailycodework.ds_microservices.controllers;

import com.dailycodework.ds_microservices.entity.Collaboration;
import com.dailycodework.ds_microservices.services.CollaborationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborations")
public class CollaborationController {

    private final CollaborationService collaborationService;

    public CollaborationController(CollaborationService collaborationService) {
        this.collaborationService = collaborationService;
    }

    @PostMapping
    public ResponseEntity<Collaboration> createCollaboration(@RequestBody Collaboration collaboration) {
        return ResponseEntity.ok(collaborationService.createCollaboration(collaboration));
    }

    @GetMapping
    public ResponseEntity<List<Collaboration>> getAllCollaborations() {
        return ResponseEntity.ok(collaborationService.getAllCollaborations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaboration> getCollaborationById(@PathVariable Long id) {
        return collaborationService.getCollaborationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collaboration> updateCollaboration(@PathVariable Long id, @RequestBody Collaboration collaboration) {
        return ResponseEntity.ok(collaborationService.updateCollaboration(id, collaboration));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollaboration(@PathVariable Long id) {
        collaborationService.deleteCollaboration(id);
        return ResponseEntity.noContent().build();
    }
}*/
package com.dailycodework.ds_microservices.controllers;

import com.dailycodework.ds_microservices.commands.CreateCollaborationCommand;
import com.dailycodework.ds_microservices.queries.GetAllCollaborationsQuery;
import com.dailycodework.ds_microservices.queries.GetCollaborationByIdQuery;
import com.dailycodework.ds_microservices.models.CollaborationView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/collaborations")
public class CollaborationController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CollaborationController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<String>> createCollaboration(@RequestBody CollaborationView request) {
        String collaborationId = UUID.randomUUID().toString();

        return commandGateway.send(new CreateCollaborationCommand(
                collaborationId,
                request.getTitle(),
                request.getDescription(),
                request.getStartDate(),
                request.getEndDate()
        )).thenApply(result -> ResponseEntity.ok("Collaboration created with ID: " + collaborationId));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<CollaborationView>>> getAllCollaborations() {
        return queryGateway.query(new GetAllCollaborationsQuery(), List.class)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<CollaborationView>> getCollaborationById(@PathVariable String id) {
        return queryGateway.query(new GetCollaborationByIdQuery(id), CollaborationView.class)
                .thenApply(collaboration -> collaboration != null
                        ? ResponseEntity.ok(collaboration)
                        : ResponseEntity.notFound().build());
    }
}
