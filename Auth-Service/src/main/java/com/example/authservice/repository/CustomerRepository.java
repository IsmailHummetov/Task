package com.example.authservice.repository;

import com.example.authservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsCustomerByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
