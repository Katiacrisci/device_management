package crisci.caterina.device_management.controller;

import crisci.caterina.device_management.DTO.EmployeeDTO;
import crisci.caterina.device_management.DTO.LoginDTO;
import crisci.caterina.device_management.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginDTO loginDTO(@RequestBody EmployeeDTO payload) {
        return new LoginDTO(authenticationService.authenticateUserAndGenerateToken(payload));
    }

}
