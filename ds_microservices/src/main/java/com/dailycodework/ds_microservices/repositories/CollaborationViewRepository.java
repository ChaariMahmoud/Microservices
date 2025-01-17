package com.dailycodework.ds_microservices.repositories;


import com.dailycodework.ds_microservices.models.CollaborationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborationViewRepository extends JpaRepository<CollaborationView, String> {
    // Vous pouvez ajouter des requêtes personnalisées ici si nécessaire
}