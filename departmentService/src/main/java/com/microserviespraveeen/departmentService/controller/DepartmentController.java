package com.microserviespraveeen.departmentService.controller;

import lombok.AllArgsConstructor;
import com.microserviespraveeen.departmentService.dto.DepartmentDto;
import com.microserviespraveeen.departmentService.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    // Build save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }
    // Build put department rest api
    @PutMapping("{code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("code") String departmentCode,@RequestBody DepartmentDto departmentDto) throws Exception{
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentCode,departmentDto);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }
    // Build delete department rest api
    @DeleteMapping("{code}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable("code") String departmentCode) throws Exception{
        DepartmentDto deletedDepartment = departmentService.deleteDepartmentByCode(departmentCode);
        return new ResponseEntity<>(deletedDepartment, HttpStatus.OK);
    }

    // Build get department rest api
    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("code") String departmentCode) throws Exception{
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
