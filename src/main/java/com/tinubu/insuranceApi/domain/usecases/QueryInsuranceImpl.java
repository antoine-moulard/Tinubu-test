package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.InsurancePersistencePort;
import com.tinubu.insuranceApi.domain.QueryInsurance;
import com.tinubu.insuranceApi.domain.exception.InsuranceNotFoundException;
import com.tinubu.insuranceApi.domain.models.Insurance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class QueryInsuranceImpl implements QueryInsurance {

    private final InsurancePersistencePort insurancePersistencePort;

    public Insurance with(UUID uuid) {
        log.info("Getting insurance with ID {}", uuid);
        return insurancePersistencePort.get(uuid)
                .orElseThrow(() -> new InsuranceNotFoundException(uuid));
    }

    public List<Insurance> all() {
        log.info("Getting all insurances");
        return insurancePersistencePort.getAll();
    }

}
