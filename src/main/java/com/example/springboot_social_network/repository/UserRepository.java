package com.example.springboot_social_network.repository;

import com.example.springboot_social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
}