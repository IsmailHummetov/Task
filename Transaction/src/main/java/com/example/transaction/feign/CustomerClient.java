package com.example.transaction.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTH-SERVICE")
public interface CustomerClient {
    @GetMapping("/customer/balance")
    Double getBalance();

    @PostMapping("/customer/top-up")
    Double addBalance(@RequestParam("amount") Double amount);

    @PostMapping("/customer/purchase")
    Double purchase(@RequestParam("amount") Double amount);
}
