package crisci.caterina.device_management.service;

import crisci.caterina.device_management.DTO.EmployeeDTO;
import crisci.caterina.device_management.exceptions.UnauthorizedException;
import crisci.caterina.device_management.models.Employee;
import crisci.caterina.device_management.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtTools jwtTools;
    public String authenticateUserAndGenerateToken(EmployeeDTO payload) {
       Employee employee = employeeService.findByEmail(payload.email());
        if (employee.getPassword().equals(payload.password())) {

            return jwtTools.createToken(employee);
        } else {

            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }

}
