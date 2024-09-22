package com.microserviespraveeen.employeeService.service;

import com.microserviespraveeen.employeeService.dto.APIResponseDto;
import com.microserviespraveeen.employeeService.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
