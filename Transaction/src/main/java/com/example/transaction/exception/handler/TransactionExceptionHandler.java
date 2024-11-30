package com.example.transaction.exception.handler;

import com.example.transaction.dto.ExceptionDto;
import com.example.transaction.exception.OverAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(OverAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleOverAmountException(OverAmountException exception) {
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
