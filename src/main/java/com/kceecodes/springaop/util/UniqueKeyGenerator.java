package com.kceecodes.springaop.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UniqueKeyGenerator {
    public static String generateUniqueKey() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("transaction-logs-" + formattedDateTime);
        return "transaction-logs-" + formattedDateTime;

    }
}
