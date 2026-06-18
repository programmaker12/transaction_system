package com.innovation.transaction_system.transaction_ingestion_service.service;

import com.innovation.transaction_system.transaction_ingestion_service.exception.InsufficientBalanceException;
import com.innovation.transaction_system.transaction_ingestion_service.login.entity.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    @Test
    void withdrawSuccess()
    {
        BankAccount account = new BankAccount(5000);

        account.withdraw(1000);

        assertEquals(
              4000, account.getBalance()
        );
    }

    @Test
    void withdrawMoreThanBalance()
    {
        BankAccount account = new BankAccount(5000);

        InsufficientBalanceException exception = assertThrows(
                InsufficientBalanceException.class,()->account.withdraw(10000));

        assertEquals("Insufficient Balance", exception.getMessage());
    }
}
