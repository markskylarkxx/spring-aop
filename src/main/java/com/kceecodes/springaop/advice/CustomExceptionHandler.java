package com.kceecodes.springaop.advice;

import com.kceecodes.springaop.exception.InsufficientBalanceException;
import com.kceecodes.springaop.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
@ControllerAdvice
public class CustomExceptionHandler {
    private  final  HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setTimestamp(Timestamp.from(Instant.now()));
        response.setStatus(badRequest);
        return new ResponseEntity<>(response,badRequest);
    }
}
