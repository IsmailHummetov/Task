package com.example.transaction.mapper;

import com.example.transaction.dto.PurchaseDto;
import com.example.transaction.dto.TransactionDto;
import com.example.transaction.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto toTransactionDto(Transaction transaction){
        return TransactionDto.builder()
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .operation(transaction.getOperation())
                .timestamp(transaction.getTimestamp())
                .build();
    }
    public static PurchaseDto toPurchaseDto(Transaction transaction){
        return PurchaseDto.builder()
                .amount(transaction.getAmount())
                .operation(transaction.getOperation())
                .timestamp(transaction.getTimestamp())
                .refund(transaction.getAmount()*0.02)
                .balance(transaction.getBalance())
                .build();
    }
}
