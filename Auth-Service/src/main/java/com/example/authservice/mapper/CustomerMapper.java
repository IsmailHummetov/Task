package com.example.authservice.mapper;

import com.example.authservice.dto.CustomerDto;
import com.example.authservice.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer){
        return CustomerDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .balance(customer.getBalance())
                .birthDate(customer.getBirthDate())
                .build();
    }
}
