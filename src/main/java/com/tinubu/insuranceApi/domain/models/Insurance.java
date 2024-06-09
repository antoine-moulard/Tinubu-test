package com.tinubu.insuranceApi.domain.models;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public record Insurance(UUID id,
                        String name,
                        InsuranceStatus insuranceStatus,
                        OffsetDateTime startDate,
                        OffsetDateTime endDate,
                        Technical technical) {

    public Insurance {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(insuranceStatus);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(technical);
    }

    public record Technical(Instant createdAt, Instant lastUpdateAt) {
        public Technical {
            Objects.requireNonNull(createdAt);
            Objects.requireNonNull(lastUpdateAt);
        }
    }
}
