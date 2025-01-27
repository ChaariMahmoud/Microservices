package com.dailycodework.ds_microservices.repositories;


import com.dailycodework.ds_microservices.entity.Collaboration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {
}
