package com.microserviespraveeen.departmentService.service.impl;

import lombok.AllArgsConstructor;
import com.microserviespraveeen.departmentService.dto.DepartmentDto;
import com.microserviespraveeen.departmentService.entity.Department;
import com.microserviespraveeen.departmentService.mapper.DepartmentMapper;
import com.microserviespraveeen.departmentService.repository.DepartmentRepository;
import com.microserviespraveeen.departmentService.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to department jpa entity
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}
