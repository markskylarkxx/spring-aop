package com.kceecodes.springaop.controller;


import com.kceecodes.springaop.model.Customer;
import com.kceecodes.springaop.model.Transaction;
import com.kceecodes.springaop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TransactionController {


    @Autowired
    private TransactionService service;

    @GetMapping("/checkBalance/{customerId}")
    public ResponseEntity<Double> checkBalance(@PathVariable String customerId) {
        double balance = service.checkBalance(customerId);
        return ResponseEntity.ok(balance);
    }

    // deposit method;
    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody Transaction transaction){
     service.deposit(transaction);
     return  new ResponseEntity<>("Deposit Completed", HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Transaction transaction) {
        service.withdraw(transaction);
        return ResponseEntity.ok("Withdrawal completed");
    }
    //get customers;

    @GetMapping("users")
    public ResponseEntity<List<Customer>> customers(){
        List<Customer> allCustomers =   service.getCustomers();
        return  new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }





}
