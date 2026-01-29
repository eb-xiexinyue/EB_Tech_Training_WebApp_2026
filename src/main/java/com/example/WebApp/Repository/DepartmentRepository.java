package com.example.WebApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebApp.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // 部署名で検索（重複チェック用）
    @Query("SELECT d FROM Department d WHERE d.department_name = :name")
    Optional<Department> findByDepartmentName(@Param("name") String name);
}