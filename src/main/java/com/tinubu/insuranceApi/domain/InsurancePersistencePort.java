package com.tinubu.insuranceApi.domain;

import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsurancePersistencePort {

    Optional<Insurance> get(UUID uuid);

    List<Insurance> getAll();

    UUID create(CreateInsurance createInsurance);

    void update(Insurance insurance);
}
