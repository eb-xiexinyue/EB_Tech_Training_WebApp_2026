package com.example.WebApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebApp.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
