package com.example.authservice.security;

import com.example.authservice.entity.Customer;
import com.example.authservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(("Email not found")));
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("customer")));
    }
}
