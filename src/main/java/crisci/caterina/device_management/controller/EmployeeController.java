package crisci.caterina.device_management.controller;

import crisci.caterina.device_management.models.Employee;
import crisci.caterina.device_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return employeeService.getAllPaged(page, size, sortBy);
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable long employeeId) throws Exception {
        return employeeService.getById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee findAndUpdate(@PathVariable long employeeId, @RequestBody Employee body) throws Exception {
        return employeeService.updateById(employeeId, body);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long employeeId) {
        employeeService.deleteById(employeeId);
    }

    @PostMapping("/{employeeId}/device")
    public Employee assignDevice(@PathVariable long employeeId, @RequestParam(name = "id") long deviceId, @RequestParam(name = "assign") boolean toBeAssigned) {
        return employeeService.assignDevice(employeeId, deviceId, toBeAssigned);
    }
}
