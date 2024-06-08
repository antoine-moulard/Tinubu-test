package com.tinubu.insuranceApi.infra;

import com.tinubu.insuranceApi.domain.Insurance;
import com.tinubu.insuranceApi.domain.InsuranceQueryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InsuranceQueryAdapter implements InsuranceQueryPort {

    private final InsuranceRepository insuranceRepository;

    @Override
    public Optional<Insurance> get(UUID uuid) {
        return insuranceRepository.findById(uuid)
                .map(InsuranceEntity::toDomain);
    }

    @Override
    public List<Insurance> getAll() {
        return insuranceRepository.findAll().stream()
                .map(InsuranceEntity::toDomain)
                .toList();
    }
}
