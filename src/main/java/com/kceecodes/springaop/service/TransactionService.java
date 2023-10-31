package com.kceecodes.springaop.service;


import com.kceecodes.springaop.exception.InsufficientBalanceException;
import com.kceecodes.springaop.model.Customer;
import com.kceecodes.springaop.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
   private  static Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private Map<String, Customer> customerMap = new HashMap<>();

    private Map<String, List<Transaction>> transactionHistory = new HashMap<>();

    // initialize the customer map;
    @EventListener(ApplicationReadyEvent.class)
    public  void initializeCustomerMap(){

        customerMap.put("customer1", new Customer("customer1", 1000.0));
        customerMap.put("customer2", new Customer("customer2", 5000.0));

    }

    public void deposit(Transaction transaction) {
        String customerId = transaction.getCustomerId();
        double amount = transaction.getAmount();

        // Check if the customer already exists in the map
        Customer customer = customerMap.get(customerId);

        if (customer == null) {
            // Create a new customer if not found
            customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setBalance(amount);
            customerMap.put(customerId, customer);
        } else {
            // Update the balance for an existing customer
            double currentBalance = customer.getBalance();
            customer.setBalance(currentBalance + amount);
        }

       // logTransaction("Deposit", customerId, amount, customer.getBalance());
        System.out.println("deposit successful");
        saveTransactionToHistory(customerId, transaction);
    }


    public void withdraw(Transaction transaction) {
        String customerId = transaction.getCustomerId();
        Customer customer = customerMap.get(customerId);
        if (customer != null) {
            double currentBalance = customer.getBalance();
            double amount = transaction.getAmount();
            if (currentBalance >= amount) {
                customer.setBalance(currentBalance - amount);
                // logTransaction("Withdraw", customerId, amount, currentBalance - amount);
                saveTransactionToHistory(customerId, transaction);
            } else {
                throw new InsufficientBalanceException("Insufficient balance, cannot process withdrawal");
            }
        }
    }



    public double checkBalance(String customerId) {
        Customer customer = customerMap.get(customerId);
        if (customer != null) {
            double balance = customer.getBalance();
            //logTransaction("Check Balance", customerId, 0, balance);
            return balance;
        } else {
            //logTransaction("Check Balance (Customer not found)", customerId, 0, 0);
            return 0;
        }
    }

    private void saveTransactionToHistory(String customerId, Transaction transaction) {
        transactionHistory.computeIfAbsent(customerId, k -> new ArrayList<>()).add(transaction);
    }


    public List <Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());


    }

    public  void getTransHistory(Transaction transaction){
        String customerId = transaction.getCustomerId() ;
       this.saveTransactionToHistory(customerId, transaction);
    }
}
