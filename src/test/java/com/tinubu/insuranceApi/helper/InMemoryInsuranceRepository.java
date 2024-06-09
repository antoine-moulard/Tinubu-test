package com.tinubu.insuranceApi.helper;

import com.tinubu.insuranceApi.domain.InsurancePersistencePort;
import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;
import com.tinubu.insuranceApi.domain.models.InsuranceStatus;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryInsuranceRepository implements InsurancePersistencePort {
    public static final UUID DEFAULT_ID = UUID.fromString("75553d4f-7e26-4f90-9860-e141fee836b3");

    @Override
    public Optional<Insurance> get(UUID uuid) {
        if (DEFAULT_ID.equals(uuid)) {
            return Optional.of(buildDefaultInsurance(uuid));
        }
        return Optional.empty();
    }

    @Override
    public List<Insurance> getAll() {
        return List.of(buildDefaultInsurance(DEFAULT_ID));
    }

    @Override
    public UUID create(CreateInsurance createInsurance) {
        return UUID.randomUUID();
    }

    @Override
    public void update(Insurance insurance) {
    }

    @NotNull
    private static Insurance buildDefaultInsurance(UUID uuid) {
        var offset = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+02:00"));
        return new Insurance(uuid, "test", InsuranceStatus.ACTIVE, offset, offset,
                new Insurance.Technical(Instant.now(), Instant.now()));
    }
}
