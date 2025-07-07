package com.example.practice.Repository;


import com.example.practice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.example.practice.Entity.User, String> {
}

