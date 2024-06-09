package com.tinubu.insuranceApi.domain.models;

import java.time.OffsetDateTime;
import java.util.Objects;

public record CreateInsurance(String name,
                              InsuranceStatus insuranceStatus,
                              OffsetDateTime startDate,
                              OffsetDateTime endDate) {

    public CreateInsurance {
        Objects.requireNonNull(name);
        Objects.requireNonNull(insuranceStatus);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
    }
}
