package com.tinubu.insuranceApi.infra;

import com.tinubu.insuranceApi.domain.InsurancePersistencePort;
import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class InsurancePersistenceAdapter implements InsurancePersistencePort {

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

    @Override
    public UUID create(CreateInsurance createInsurance) {
        var entity = InsuranceEntity.from(createInsurance);
        var saved = this.insuranceRepository.save(entity);
        return saved.getId();
    }

    @Override
    public void update(Insurance insurance) {
        var entity = InsuranceEntity.from(insurance);
        entity.setUpdatedAt(Instant.now());
        this.insuranceRepository.saveAndFlush(entity);
    }
}
