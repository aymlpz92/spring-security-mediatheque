package fr.simplon.springsecuritymediatheque.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.springsecuritymediatheque.model.dtos.users.LoginRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.LoginResponse;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterResponse;
import fr.simplon.springsecuritymediatheque.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.loginUser(request), HttpStatus.ACCEPTED);
    }
}
