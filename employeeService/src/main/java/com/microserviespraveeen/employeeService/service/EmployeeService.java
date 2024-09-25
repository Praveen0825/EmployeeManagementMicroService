package com.microserviespraveeen.employeeService.service;

import com.microserviespraveeen.employeeService.dto.APIResponseDto;
import com.microserviespraveeen.employeeService.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployeeById(Long employeeId,EmployeeDto EmployeeDto) throws Exception;
    public EmployeeDto deleteEmployeeById(Long employeeId) throws Exception;

    APIResponseDto getEmployeeById(Long employeeId);
}
