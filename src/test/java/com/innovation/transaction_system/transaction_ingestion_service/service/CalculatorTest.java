package com.innovation.transaction_system.transaction_ingestion_service.service;


import com.innovation.transaction_system.transaction_ingestion_service.login.service.Study;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculatorTest {

    @Test
    void shouldAddTwoPositiveNumbers()
    {
        Study calculator = new Study();
        int result = calculator.add(10, 20);
        assertEquals(50, result);
    }
}
