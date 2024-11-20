package hu.unideb.inf.notemanager.controller;

import hu.unideb.inf.notemanager.dto.LoginDto;
import hu.unideb.inf.notemanager.dto.RegistrationDto;
import hu.unideb.inf.notemanager.exception.InvalidCredentialsException;
import hu.unideb.inf.notemanager.exception.UserAlreadyExistsException;
import hu.unideb.inf.notemanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationDto registrationDto) {
        try {
            authService.registration(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful.");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            authService.login(loginDto);  // Assuming this method does the login internally

            // If no exception was thrown, assume login was successful
            return ResponseEntity.ok("Login successful.");
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred." + e);
        }
    }
}
