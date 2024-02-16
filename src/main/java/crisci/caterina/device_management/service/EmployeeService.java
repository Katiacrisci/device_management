package crisci.caterina.device_management.service;

import crisci.caterina.device_management.exceptions.NotFoundException;
import crisci.caterina.device_management.models.Employee;
import crisci.caterina.device_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee save(Employee device) {
        return employeeRepository.save(device);
    }

    public Employee updateById(Long id, Employee device) {
        if (getById(id) == null) {
            return null;
        }

        return employeeRepository.save(device);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
