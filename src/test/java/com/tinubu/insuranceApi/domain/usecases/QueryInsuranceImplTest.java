package com.tinubu.insuranceApi.domain.usecases;

import com.tinubu.insuranceApi.domain.exception.InsuranceNotFoundException;
import com.tinubu.insuranceApi.helper.InMemoryInsuranceRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueryInsuranceImplTest {

    private final QueryInsuranceImpl queryInsurance;

    public QueryInsuranceImplTest() {
        var repo = new InMemoryInsuranceRepository();
        this.queryInsurance = new QueryInsuranceImpl(repo);
    }


    @Test
    void it_should_correctly_retrieve_insurance() {
        assertThatCode(() -> this.queryInsurance.with(InMemoryInsuranceRepository.DEFAULT_ID))
                .doesNotThrowAnyException();
    }

    @Test
    void it_should_throw_when_insurance_not_found() {
        assertThatThrownBy(() -> this.queryInsurance.with(UUID.fromString("018ffed8-615c-768c-963f-1ed74505267b")))
                .isInstanceOf(InsuranceNotFoundException.class);
    }
}
