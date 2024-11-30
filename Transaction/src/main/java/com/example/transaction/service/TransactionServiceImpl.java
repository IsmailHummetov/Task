package com.example.transaction.service;

import com.example.transaction.dto.PurchaseDto;
import com.example.transaction.dto.TransactionDto;
import com.example.transaction.entity.Transaction;
import com.example.transaction.exception.OverAmountException;
import com.example.transaction.feign.CustomerClient;
import com.example.transaction.mapper.TransactionMapper;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.util.JwtDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private JwtDecoder jwtDecoder;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDto topUp(HttpServletRequest request, double amount) {
        Transaction transaction = createTransaction(request, amount);
        transaction.setBalance(customerClient.addBalance(amount));
        transaction.setOperation("Top-Up");
        transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDto(transaction);
    }

    @Override
    public PurchaseDto purchase(HttpServletRequest request, double amount) {
        Transaction transaction = createTransaction(request, amount);
        Double balance = customerClient.getBalance();
        if (amount > balance)
            throw new OverAmountException("Balance is insufficient");
        transaction.setBalance(customerClient.purchase(amount));
        transaction.setOperation("Purchase");
        transactionRepository.save(transaction);
        return TransactionMapper.toPurchaseDto(transaction);
    }

    @Override
    public List<TransactionDto> transactions(HttpServletRequest request) {
        return transactionRepository.findByUuid(jwtDecoder.extractUUID(request))
                .stream().map(TransactionMapper::toTransactionDto).toList();
    }

    private Transaction createTransaction(HttpServletRequest request, double amount) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String v = time.format(formatter);
        time = LocalDateTime.parse(v, formatter);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(time);
        transaction.setUuid(jwtDecoder.extractUUID(request));
        return transaction;
    }
}
