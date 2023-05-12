package com.abaimov.jwtshop.auth;

import com.abaimov.jwtshop.dto.LoginResponseDTO;
import com.abaimov.jwtshop.dto.RegistrationDTO;
import com.abaimov.jwtshop.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authService.register(body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authService.loginUser(body.getEmail(), body.getPassword());
    }
}
