package com.dailycodework.ds_microservices.services;

import com.dailycodework.ds_microservices.entity.Collaboration;

import java.util.List;
import java.util.Optional;

public interface CollaborationService {
    Collaboration createCollaboration(Collaboration collaboration);
    List<Collaboration> getAllCollaborations();
    Optional<Collaboration> getCollaborationById(Long id);
    Collaboration updateCollaboration(Long id, Collaboration collaboration);
    void deleteCollaboration(Long id);
}