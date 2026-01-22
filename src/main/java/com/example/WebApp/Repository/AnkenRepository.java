package com.example.WebApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebApp.Entity.Anken;

public interface AnkenRepository extends JpaRepository<Anken, Long> {
	@Query(value = "SELECT anken_id,anken_name FROM Anken WHERE department.department_id = :deptId")
	List<Anken> findByDepartmentId(@Param("deptId") Long departmentId);
}
