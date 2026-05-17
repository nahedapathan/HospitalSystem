package com.hms.HospitalManamentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.HospitalManamentSystem.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
