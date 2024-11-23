package com.dailycodework.usermicroservice.repositories;


import com.dailycodework.usermicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

