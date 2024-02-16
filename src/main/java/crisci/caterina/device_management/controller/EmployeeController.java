package crisci.caterina.device_management.controller;

import crisci.caterina.device_management.models.Employee;
import crisci.caterina.device_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Employee saveEmployee(@RequestBody Employee body) throws Exception {
        System.out.println(body);
        return employeeService.save(body);
    }

    @GetMapping("")
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{deviceId}")
    public Employee getById(@PathVariable long deviceId) throws Exception {
        return employeeService.getById(deviceId);
    }

    @PutMapping("/{deviceId}")
    public Employee findAndUpdate(@PathVariable long deviceId, @RequestBody Employee body) throws Exception {
        return employeeService.updateById(deviceId, body);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long deviceId) {
        employeeService.deleteById(deviceId);
    }
}
