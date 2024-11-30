package com.example.authservice.service.Impl;

import com.example.authservice.dto.LoginDto;
import com.example.authservice.dto.RegisterDto;
import com.example.authservice.entity.Customer;
import com.example.authservice.repository.CustomerRepository;
import com.example.authservice.security.JwtUtil;
import com.example.authservice.service.Inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterDto register) {
        Customer customer = Customer.builder()
                .name(register.getName())
                .surname(register.getSurname())
                .email(register.getEmail())
                .balance(50.0)
                .birthDate(register.getBirthDate())
                .password(passwordEncoder.encode(register.getPassword()))
                .build();
        customerRepository.save(customer);
        LoginDto loginDto = LoginDto.builder()
                .email(register.getEmail())
                .password(register.getPassword())
                .build();
        return login(loginDto);
    }

    @Override
    public String login(LoginDto login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Customer customer = customerRepository.findByEmail(login.getEmail()).orElse(null);
        assert customer != null;
        return jwtUtil.generateToken(login.getEmail(),customer.getId());
    }
}
