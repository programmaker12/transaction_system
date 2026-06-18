package com.innovation.transaction_system.transaction_ingestion_service.service;

import com.innovation.transaction_system.transaction_ingestion_service.login.service.Study;
import io.netty.util.internal.StringUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    Study study = new Study();
    @Test
    void studReverseString()
    {
        String result = study.reverse("hello");
        assertEquals("olleh", result);
    }

    @Test
    void shouldReturnNullForNullInput()
    {
        String result = study.reverse(null);
        assertNull(null);
    }

    @Test
    void reverseMultipleCases()
    {
        assertAll(
                () -> assertEquals("olleh", study.reverse("hello")),
                () -> assertEquals("", study.reverse("")),
                () -> assertNull(study.reverse(null)),
                () -> assertEquals("A", study.reverse("A"))
        );
    }
}
