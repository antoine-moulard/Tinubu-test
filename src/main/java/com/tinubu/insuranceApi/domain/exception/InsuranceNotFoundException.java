package com.tinubu.insuranceApi.domain.exception;

import java.util.UUID;

public class InsuranceNotFoundException extends RuntimeException {

    public InsuranceNotFoundException(UUID insuranceId) {
        super("Could not found Insurance with ID %s".formatted(insuranceId));
    }
}
