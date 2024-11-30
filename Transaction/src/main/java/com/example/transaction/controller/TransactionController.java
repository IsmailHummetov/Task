package com.example.transaction.controller;

import com.example.transaction.dto.PurchaseDto;
import com.example.transaction.dto.TransactionDto;
import com.example.transaction.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransactions(HttpServletRequest request){
        return ResponseEntity.ok(transactionService.transactions(request));
    }

    @PostMapping("/top-up")
    public ResponseEntity<TransactionDto> addBalance(
            HttpServletRequest request, @RequestParam("amount") Double amount) {
        return ResponseEntity.ok(transactionService.topUp(request, amount));
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseDto> purchase(
            HttpServletRequest request, @RequestParam("amount") Double amount) {
        return ResponseEntity.ok(transactionService.purchase(request,amount));
    }

}
