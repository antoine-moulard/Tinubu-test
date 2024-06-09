package com.tinubu.insuranceApi.domain.models;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public record UpdateInsurance(UUID id,
                              String name,
                              InsuranceStatus insuranceStatus,
                              OffsetDateTime startDate,
                              OffsetDateTime endDate) {
    public UpdateInsurance {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(insuranceStatus);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
    }

    public Insurance with(Insurance insurance) {
        if (!Objects.equals(insurance.id(), this.id())) {
            throw new IllegalArgumentException("Target and source must have the same ID when updating an insurance," +
                    "found %s and expected %s".formatted(insurance.id(), this.id()));
        }
        return new Insurance(this.id(),
                this.name(),
                this.insuranceStatus,
                this.startDate,
                this.endDate,
                insurance.technical()
        );
    }
}
