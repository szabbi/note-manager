package hu.unideb.inf.notemanager.controller;

import hu.unideb.inf.notemanager.dto.LoginDto;
import hu.unideb.inf.notemanager.dto.RegistrationDto;
import hu.unideb.inf.notemanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDto registrationDto) {
        authService.registration(registrationDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
    }
}
