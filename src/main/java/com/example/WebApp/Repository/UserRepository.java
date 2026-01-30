package com.example.WebApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebApp.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}