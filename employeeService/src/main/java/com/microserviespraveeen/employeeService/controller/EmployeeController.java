package com.microserviespraveeen.employeeService.controller;

import lombok.AllArgsConstructor;
import com.microserviespraveeen.employeeService.dto.APIResponseDto;
import com.microserviespraveeen.employeeService.dto.EmployeeDto;
import com.microserviespraveeen.employeeService.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto employeeDto) throws Exception{
        EmployeeDto updatedEmployee = employeeService.updateEmployeeById(employeeId,employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") Long employeeId) throws Exception{
        EmployeeDto deletedEmployee = employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
