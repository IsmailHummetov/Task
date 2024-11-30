package com.example.transaction.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class FeignClientJwtInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    public FeignClientJwtInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            template.header("Authorization", authorizationHeader);
        }
    }
}