package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.Insurance;

import java.util.List;
import java.util.UUID;

public interface QueryInsurance {

    Insurance with(UUID uuid);

    List<Insurance> all();
}
