package com.tinubu.insuranceApi.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuranceQueryPort {

    Optional<Insurance> get(UUID uuid);

    List<Insurance> getAll();

}
