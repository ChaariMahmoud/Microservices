package com.dailycodework.ds_microservices.servicesImpl;

import com.dailycodework.ds_microservices.entity.Collaboration;
import com.dailycodework.ds_microservices.repositories.CollaborationRepository;
import com.dailycodework.ds_microservices.services.CollaborationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollaborationServiceImpl implements CollaborationService {

    private final CollaborationRepository collaborationRepository;

    public CollaborationServiceImpl(CollaborationRepository collaborationRepository) {
        this.collaborationRepository = collaborationRepository;
    }

    // Define the Circuit Breaker here
    private static final String CIRCUIT_BREAKER_NAME = "collaborationCircuitBreaker";

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "createCollaborationFallback")
    public Collaboration createCollaboration(Collaboration collaboration) {
        return collaborationRepository.save(collaboration);
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getAllCollaborationsFallback")
    public List<Collaboration> getAllCollaborations() {
        return collaborationRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getCollaborationByIdFallback")
    public Optional<Collaboration> getCollaborationById(Long id) {
        return collaborationRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "updateCollaborationFallback")
    public Collaboration updateCollaboration(Long id, Collaboration collaboration) {
        return collaborationRepository.findById(id)
                .map(existingCollaboration -> {
                    existingCollaboration.setTitle(collaboration.getTitle());
                    existingCollaboration.setDescription(collaboration.getDescription());
                    existingCollaboration.setStartDate(collaboration.getStartDate());
                    existingCollaboration.setEndDate(collaboration.getEndDate());
                    existingCollaboration.setStatus(collaboration.getStatus());
                    existingCollaboration.setAssignedTo(collaboration.getAssignedTo());
                    return collaborationRepository.save(existingCollaboration);
                })
                .orElseThrow(() -> new RuntimeException("Collaboration not found with id " + id));
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "deleteCollaborationFallback")
    public void deleteCollaboration(Long id) {
        collaborationRepository.deleteById(id);
    }

    // Fallback methods
    public Collaboration createCollaborationFallback(Collaboration collaboration, Throwable throwable) {
        // Return a default value or log the failure
        return new Collaboration();
    }

    public List<Collaboration> getAllCollaborationsFallback(Throwable throwable) {
        // Return an empty list in case of failure
        return List.of();
    }

    public Optional<Collaboration> getCollaborationByIdFallback(Long id, Throwable throwable) {
        // Return an empty optional in case of failure
        return Optional.empty();
    }

    public Collaboration updateCollaborationFallback(Long id, Collaboration collaboration, Throwable throwable) {
        // Return a default value or handle fallback logic
        return new Collaboration();
    }

    public void deleteCollaborationFallback(Long id, Throwable throwable) {
        // Handle failure case (e.g., log the error)
    }
}
