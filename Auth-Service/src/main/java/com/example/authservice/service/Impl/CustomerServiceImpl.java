package com.example.authservice.service.Impl;

import com.example.authservice.dto.CustomerDto;
import com.example.authservice.entity.Customer;
import com.example.authservice.mapper.CustomerMapper;
import com.example.authservice.repository.CustomerRepository;
import com.example.authservice.security.JwtUtil;
import com.example.authservice.service.Inter.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public CustomerDto getCustomer(HttpServletRequest request) {
        UUID uuid = jwtUtil.extractUUID(request);
        Customer customer = customerRepository.findById(uuid).orElse(null);
        assert customer != null;
        return CustomerMapper.toCustomerDto(customer);
    }

    @Override
    public Double getBalance(HttpServletRequest request) {
        UUID uuid = jwtUtil.extractUUID(request);
        Customer customer = customerRepository.findById(uuid).orElse(null);
        if (customer!=null)
            return customer.getBalance();
        return 0.0;
    }

    @Override
    public Double addBalance(HttpServletRequest request, Double amount) {
        UUID uuid = jwtUtil.extractUUID(request);
        Customer customer = customerRepository.findById(uuid).orElse(null);
        assert customer != null;
        customer.setBalance(customer.getBalance()+amount);
        customerRepository.save(customer);
        return customer.getBalance();
    }

    @Override
    public Double purchase(HttpServletRequest request, Double amount) {
        UUID uuid = jwtUtil.extractUUID(request);
        Customer customer = customerRepository.findById(uuid).orElse(null);
        assert customer != null;
        customer.setBalance(customer.getBalance()-amount+(amount*0.02));
        customerRepository.save(customer);
        return customer.getBalance();
    }
}
