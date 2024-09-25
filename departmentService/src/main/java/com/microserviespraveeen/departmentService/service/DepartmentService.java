package com.microserviespraveeen.departmentService.service;

import com.microserviespraveeen.departmentService.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto deleteDepartmentByCode(String departmentCode) throws Exception;
    DepartmentDto updateDepartment(String departmentCode,DepartmentDto departmentDto) throws Exception;

    DepartmentDto getDepartmentByCode(String code) throws Exception;
}
