package com.microserviespraveeen.employeeService.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import com.microserviespraveeen.employeeService.dto.APIResponseDto;
import com.microserviespraveeen.employeeService.dto.DepartmentDto;
import com.microserviespraveeen.employeeService.dto.EmployeeDto;
import com.microserviespraveeen.employeeService.dto.EmployeeDto;
import com.microserviespraveeen.employeeService.entity.Employee;
import com.microserviespraveeen.employeeService.mapper.EmployeeMapper;
import com.microserviespraveeen.employeeService.repository.EmployeeRepository;
import com.microserviespraveeen.employeeService.service.APIClient;
import com.microserviespraveeen.employeeService.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

   // private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee saveDEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveDEmployee);
    }
    @Override
    public EmployeeDto deleteEmployeeById(Long employeeId) throws Exception{
        Optional<Employee> employeeOpt= employeeRepository.findById(employeeId);
        if (!employeeOpt.isPresent()) {
            throw new Exception("Employee does not exist");
        }

        employeeRepository.deleteById(employeeId);

        return EmployeeMapper.mapToEmployeeDto(employeeOpt.get());
    }
    @Override
    public EmployeeDto updateEmployeeById(Long employeeId,EmployeeDto EmployeeDto) throws Exception{
        // convert Employee dto to Employee jpa entity
        Employee employee = EmployeeMapper.mapToEmployee(EmployeeDto);
        Optional<Employee> employeeOpt= employeeRepository.findById(employeeId);
        if (!employeeOpt.isPresent()) {
            throw new Exception("Employee does not exist");
        }
        Employee employeeDB=employeeOpt.get();

        employeeDB.setFirstName(employee.getFirstName());
        employeeDB.setLastName(employee.getLastName());
        employeeDB.setEmail(employee.getEmail());
        employeeDB.setDepartmentCode(employee.getDepartmentCode());
        employeeDB.setOrganizationCode(employee.getOrganizationCode());

        Employee savedEmployee = employeeRepository.save(employeeDB);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

      //  DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto EmployeeDto = webClient.get()
                .uri("http://localhost:8084/api/Employees/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setEmployee(EmployeeDto);
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}
