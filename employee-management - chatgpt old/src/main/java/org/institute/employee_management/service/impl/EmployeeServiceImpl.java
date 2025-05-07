package org.institute.employee_management.service.impl;


import lombok.RequiredArgsConstructor;
import org.institute.employee_management.dto.EmployeeDTO;
import org.institute.employee_management.exception.ResourceNotFoundException;
import org.institute.employee_management.model.Employee;
import org.institute.employee_management.repository.EmployeeRepository;
import org.institute.employee_management.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    private EmployeeDTO mapToDTO(Employee emp) {
        return new EmployeeDTO(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    private Employee mapToEntity(EmployeeDTO dto) {
        return new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee saved = repository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID " + id));
        return mapToDTO(emp);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID " + id));

        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());

        return mapToDTO(repository.save(emp));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID " + id));
        repository.delete(emp);
    }
}
