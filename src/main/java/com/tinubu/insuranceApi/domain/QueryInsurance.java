package com.tinubu.insuranceApi.domain;

import com.tinubu.insuranceApi.domain.models.Insurance;

import java.util.List;
import java.util.UUID;

public interface QueryInsurance {

    Insurance with(UUID uuid);

    List<Insurance> all();
}
