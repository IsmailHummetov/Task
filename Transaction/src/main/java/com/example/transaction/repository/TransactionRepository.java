package com.example.transaction.repository;

import com.example.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUuid(UUID uuid);
}
