package com.example.transaction.service;

import com.example.transaction.dto.PurchaseDto;
import com.example.transaction.dto.TransactionDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TransactionService {
    TransactionDto topUp(HttpServletRequest request, double amount);

    PurchaseDto purchase(HttpServletRequest request, double amount);

    List<TransactionDto> transactions(HttpServletRequest request);
}
