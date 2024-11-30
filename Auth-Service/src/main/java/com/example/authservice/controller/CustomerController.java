package com.example.authservice.controller;

import com.example.authservice.dto.CustomerDto;
import com.example.authservice.security.JwtUtil;
import com.example.authservice.service.Inter.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/profile")
    public ResponseEntity<CustomerDto> profile(HttpServletRequest request) {
        return ResponseEntity.ok(customerService.getCustomer(request));
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(HttpServletRequest request) {
        return ResponseEntity.ok(customerService.getBalance(request));
    }

    @PostMapping("/top-up")
    public ResponseEntity<Double> addBalance(HttpServletRequest request, @RequestParam("amount") Double amount) {
        return ResponseEntity.ok(customerService.addBalance(request, amount));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Double> purchase(HttpServletRequest request, @RequestParam("amount") Double amount){
        return ResponseEntity.ok(customerService.purchase(request, amount));
    }


}
