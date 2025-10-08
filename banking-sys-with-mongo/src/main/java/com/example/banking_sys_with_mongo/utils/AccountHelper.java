package com.example.banking_sys_with_mongo.utils;

import java.util.Random;
import java.util.UUID;

public class AccountHelper {

    static long number = 0;

    public static String generateAccountNumber()
    {
        Random random = new Random();
        number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }
}
