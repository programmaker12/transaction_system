package com.innovation.transaction_system.transaction_ingestion_service.service;

import com.innovation.transaction_system.transaction_ingestion_service.login.service.Study;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VotingServiceTest {

    Study study = new Study();

    @Test
    void shouldAllowVotingForAgeGreaterThan18()
    {
        boolean result = study.isEligibleForVoting(25);
        assertTrue(result);
    }

    @Test
    void shouldAllowVotingForAgeLowerThan18()
    {
        boolean result = study.isEligibleForVoting(11);
        assertFalse(result);
    }
}
