package com.example.authservice.service.Inter;

import com.example.authservice.dto.CustomerDto;
import jakarta.servlet.http.HttpServletRequest;

public interface CustomerService {
    CustomerDto getCustomer(HttpServletRequest request);
    Double getBalance(HttpServletRequest request);
    Double addBalance(HttpServletRequest request,Double amount);
    Double purchase(HttpServletRequest request,Double amount);
}
