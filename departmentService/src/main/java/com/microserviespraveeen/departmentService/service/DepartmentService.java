package com.microserviespraveeen.departmentService.service;

import com.microserviespraveeen.departmentService.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
