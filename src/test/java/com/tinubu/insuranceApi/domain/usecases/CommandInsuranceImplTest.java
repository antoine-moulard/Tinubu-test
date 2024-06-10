package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.InsurancePersistencePort;
import com.tinubu.insuranceApi.domain.exception.InsuranceNotFoundException;
import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.InsuranceStatus;
import com.tinubu.insuranceApi.domain.models.UpdateInsurance;
import com.tinubu.insuranceApi.helper.InMemoryInsuranceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandInsuranceImplTest {

    private final CommandInsuranceImpl commandInsurance;

    CommandInsuranceImplTest() {
        InsurancePersistencePort repository = new InMemoryInsuranceRepository();
        this.commandInsurance = new CommandInsuranceImpl(repository);
    }

    @Test
    void should_correctly_create_insurance_from_given_id() {
        var offset = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+02:00"));
        var createInsurance = new CreateInsurance("test", InsuranceStatus.ACTIVE, offset, offset);

        assertThatCode(() -> this.commandInsurance.create(createInsurance))
                .doesNotThrowAnyException();
    }

    @Test
    void should_correctly_update_insurance() {
        var offset = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+02:00"));
        var updateInsurance = new UpdateInsurance(InMemoryInsuranceRepository.DEFAULT_ID,
                "test",
                InsuranceStatus.ACTIVE,
                offset,
                offset);

        assertThatCode(() -> this.commandInsurance.update(updateInsurance))
                .doesNotThrowAnyException();
    }

    @Test
    void should_throw_when_updated_insurance_does_not_exist() {
        var offset = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+02:00"));
        var updateInsurance = new UpdateInsurance(UUID.fromString("018ffed8-615c-768c-963f-1ed74505267b"),
                "test",
                InsuranceStatus.ACTIVE,
                offset,
                offset);
        assertThatThrownBy(() -> this.commandInsurance.update(updateInsurance))
                .isInstanceOf(InsuranceNotFoundException.class);
    }

}
