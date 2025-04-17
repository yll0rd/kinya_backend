package com.kinya.kinya_backend.auth.controller;

import com.kinya.kinya_backend.auth.dtos.JwtDto;
import com.kinya.kinya_backend.auth.dtos.SignInDto;
import jakarta.validation.Valid;
import com.kinya.kinya_backend.auth.dtos.SignUpDto;
import com.kinya.kinya_backend.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AuthService authService;

//    @Autowired
//    private TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpDto data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new JwtDto(null));
        }
        authService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        String accessToken = authService.login(data);
        return ResponseEntity.ok(new JwtDto(accessToken));

    }
}


