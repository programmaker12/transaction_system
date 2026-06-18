package com.innovation.transaction_system.transaction_ingestion_service.login.service;

public class Study {

    public int add(int a, int b) {
        return a + b;
    }

    public String reverse(String str) {

        if (str == null) {
            return null;
        }

        return new StringBuilder(str)
                .reverse()
                .toString();
    }

    public boolean isEligibleForVoting(int age) {

        if (age < 0) {
            return false;
        }

        return age >= 18;
    }
}
