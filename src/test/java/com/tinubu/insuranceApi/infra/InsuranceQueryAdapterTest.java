package com.tinubu.insuranceApi.infra;

import com.tinubu.insuranceApi.domain.InsuranceStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class InsuranceQueryAdapterTest {
    private final InsuranceRepository insuranceRepository;
    private final InsuranceQueryAdapter insuranceQueryAdapter;
    private UUID savedInsurance;

    InsuranceQueryAdapterTest(@Autowired InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
        insuranceQueryAdapter = new InsuranceQueryAdapter(this.insuranceRepository);
    }

    @BeforeEach
    void beforeEach() {
        var startDate = LocalDateTime.of(2024, Month.JANUARY, 15, 0, 0, 0);
        var endDate = LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0, 0);
        var insuranceEntity = new InsuranceEntity(
                "Test",
                InsuranceStatus.ACTIVE,
                OffsetDateTime.of(startDate, ZoneOffset.of("+02:00")),
                OffsetDateTime.of(endDate, ZoneOffset.of("+02:00"))

        );
        var insurance = this.insuranceRepository.saveAndFlush(insuranceEntity);
        this.savedInsurance = insurance.getId();
    }

    @Test
    void it_should_correctly_retrieve_insurance() {
        var optionalInsurance = this.insuranceQueryAdapter.get(this.savedInsurance);

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
        var insuranceEntities = this.insuranceQueryAdapter.getAll();

        assertThat(insuranceEntities).isNotEmpty();
    }

    @AfterEach
    void afterEach() {
        this.insuranceRepository.deleteAll();
    }
}
