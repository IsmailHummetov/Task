package com.example.authservice.controller;

import com.example.authservice.dto.LoginDto;
import com.example.authservice.dto.RegisterDto;
import com.example.authservice.repository.CustomerRepository;
import com.example.authservice.service.Inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (customerRepository.existsCustomerByEmail(registerDto.getEmail())){
            return ResponseEntity.ok("This Email is already used");
        }
        return ResponseEntity.ok("Customer registered successfully"+"\nToken: "
                +authService.register(registerDto));
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok("Token: "+authService.login(loginDto));
    }
}
