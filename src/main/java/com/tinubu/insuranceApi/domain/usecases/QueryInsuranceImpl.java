package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.Insurance;
import com.tinubu.insuranceApi.domain.InsuranceQueryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class QueryInsuranceImpl implements QueryInsurance {

    private final InsuranceQueryPort insuranceQueryPort;

    public Insurance with(UUID uuid) {
        log.info("Getting insurance with ID {}", uuid);
        return insuranceQueryPort.get(uuid)
                .orElseThrow();
    }

    public List<Insurance> all() {
        log.info("Getting all insurances");
        return insuranceQueryPort.getAll();
    }

}
