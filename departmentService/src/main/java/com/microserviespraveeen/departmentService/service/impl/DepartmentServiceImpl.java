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
    //@Override
    public DepartmentDto updateDepartment(String departmentCode,DepartmentDto departmentDto) throws Exception{

        // convert department dto to department jpa entity
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department departmentDB = departmentRepository.findByDepartmentCode(departmentCode);
        if (departmentDB == null) {
            throw new Exception("Department does not exist");
        }

        departmentDB.setDepartmentName(department.getDepartmentName());
        departmentDB.setDepartmentDescription(department.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(departmentDB);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    //@Override
    public DepartmentDto deleteDepartmentByCode(String departmentCode) throws Exception{

        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department == null) {
            throw new Exception("Department does not exist");
        }
        departmentRepository.deleteByDepartmentCode(departmentCode);

        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) throws Exception{

        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department == null) {
            throw new Exception("Department does not exist");
        }

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}
