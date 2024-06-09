package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.CommandInsurance;
import com.tinubu.insuranceApi.domain.InsurancePersistencePort;
import com.tinubu.insuranceApi.domain.exception.InsuranceNotFoundException;
import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.UpdateInsurance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandInsuranceImpl implements CommandInsurance {

    private final InsurancePersistencePort insurancePersistencePort;

    @Override
    public UUID create(CreateInsurance creationInsurance) {
        log.info("Creating new insurance '{}'", creationInsurance.name());
        return insurancePersistencePort.create(creationInsurance);
    }

    @Override
    public void update(UpdateInsurance update) {
        log.info("Updating insurance '{}'", update.id());
        var insurance = this.insurancePersistencePort.get(update.id())
                .orElseThrow(() -> new InsuranceNotFoundException(update.id()));
        insurancePersistencePort.update(update.with(insurance));
    }
}
