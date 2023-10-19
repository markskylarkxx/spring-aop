package com.kceecodes.springaop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private  String customerId;
    private  String TransactionType;
    private double amount;
    private LocalDateTime localDateTime;


}
