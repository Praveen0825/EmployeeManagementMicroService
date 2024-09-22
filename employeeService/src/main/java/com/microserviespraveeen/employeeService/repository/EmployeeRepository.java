package com.microserviespraveeen.employeeService.repository;

import com.microserviespraveeen.employeeService.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
