package com.innovation.transaction_system.transaction_ingestion_service.login.entity;

import com.innovation.transaction_system.transaction_ingestion_service.exception.InsufficientBalanceException;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {

        if(amount > balance) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance"
            );
        }

        balance = balance - amount;
    }

    public double getBalance() {
        return balance;
    }
}
