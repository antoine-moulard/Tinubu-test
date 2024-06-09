package com.tinubu.insuranceApi.domain;

import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.UpdateInsurance;

import java.util.UUID;

public interface CommandInsurance {
    UUID create(CreateInsurance create);

    void update(UpdateInsurance update);
}
