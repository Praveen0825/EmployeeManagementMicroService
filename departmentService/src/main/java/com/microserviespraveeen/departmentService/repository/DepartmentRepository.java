package com.microserviespraveeen.departmentService.repository;

import com.microserviespraveeen.departmentService.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentCode(String departmentCode);
}
