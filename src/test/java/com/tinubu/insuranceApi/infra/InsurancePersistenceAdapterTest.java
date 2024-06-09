package com.tinubu.insuranceApi.infra;

import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;
import com.tinubu.insuranceApi.domain.models.InsuranceStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.*;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InsurancePersistenceAdapterTest {
    private final InsuranceRepository insuranceRepository;
    private final InsurancePersistenceAdapter insurancePersistenceAdapter;
    private UUID savedInsurance;

    InsurancePersistenceAdapterTest(@Autowired InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
        insurancePersistenceAdapter = new InsurancePersistenceAdapter(this.insuranceRepository);
    }

    @BeforeEach
    void beforeEach() {
        var insuranceEntity = buildEntity("Default");
        var insurance = this.insuranceRepository.saveAndFlush(insuranceEntity);
        this.savedInsurance = insurance.getId();
    }

    @Test
    void it_should_correctly_retrieve_insurance() {
        var optionalInsurance = this.insurancePersistenceAdapter.get(this.savedInsurance);

        assertThat(optionalInsurance).isPresent().get().satisfies(insurance -> {
            assertThat(insurance.id()).isNotNull();
            assertThat(insurance.name()).isNotEmpty();
            assertThat(insurance.insuranceStatus()).isNotNull();
            assertThat(insurance.technical().createdAt()).isNotNull();
            assertThat(insurance.technical().lastUpdateAt()).isNotNull();
        });
    }

    @Test
    void it_should_correctly_retrieve_all_insurances() {
        var insuranceEntities = this.insurancePersistenceAdapter.getAll();

        assertThat(insuranceEntities).isNotEmpty().hasSize(1);
    }

    @Test
    void it_should_correctly_save_insurance() {
        this.insuranceRepository.deleteAll();
        var insurance = buildEntity("New Insurance");
        var createCommand = new CreateInsurance(insurance.getName(), InsuranceStatus.ACTIVE, insurance.getStartDate(), insurance.getEndDate());

        this.insurancePersistenceAdapter.create(createCommand);

        var resultAfterSave = this.insuranceRepository.findAll();
        assertThat(resultAfterSave).hasSize(1);
    }

    @Test
    void it_should_correctly_update_insurance() {
        var defaultEntity = buildEntity("Update Insurance");
        var updated = new Insurance(this.savedInsurance, "Updated", InsuranceStatus.INACTIVE, defaultEntity.getStartDate(), defaultEntity.getEndDate(),
                new Insurance.Technical(Instant.now(), Instant.now())
        );

        this.insurancePersistenceAdapter.update(updated);


        var result = this.insuranceRepository.findById(savedInsurance);
        assertThat(result).get().satisfies(insurance -> {
            assertThat(insurance.getName()).isEqualTo("Updated");
            assertThat(insurance.getInsuranceStatus()).isEqualTo(InsuranceStatus.INACTIVE);
        });
    }

    @AfterEach
    void afterEach() {
        this.insuranceRepository.deleteAll();
    }

    private static InsuranceEntity buildEntity(String name) {
        var startDate = LocalDateTime.of(2024, Month.JANUARY, 15, 0, 0, 0);
        var endDate = LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0, 0);
        return new InsuranceEntity(
                name,
                InsuranceStatus.ACTIVE,
                OffsetDateTime.of(startDate, ZoneOffset.of("+02:00")),
                OffsetDateTime.of(endDate, ZoneOffset.of("+02:00"))

        );
    }
}
