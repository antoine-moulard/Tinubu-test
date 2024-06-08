package com.tinubu.insuranceApi.web;

import com.tinubu.insuranceApi.domain.Insurance;
import com.tinubu.insuranceApi.domain.usecases.QueryInsurance;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.openapitools.api.InsuranceApi;
import org.openapitools.model.InsuranceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InsuranceController implements InsuranceApi {

    private final QueryInsurance queryInsurance;

    @Override
    public ResponseEntity<List<InsuranceDto>> getInsurances() {
        var insurances = queryInsurance.all().stream()
                .map(InsuranceController::toInsuranceDto)
                .toList();
        return ResponseEntity.ok(insurances);
    }

    @Override
    public ResponseEntity<InsuranceDto> getInsurance(UUID insuranceId) {
        var insurance = this.queryInsurance.with(insuranceId);
        return ResponseEntity.ok(toInsuranceDto(insurance));
    }

    @Override
    public ResponseEntity<List<InsuranceDto>> createInsurance(InsuranceDto insurance) {
        return InsuranceApi.super.createInsurance(insurance);
    }

    @Override
    public ResponseEntity<List<InsuranceDto>> updateInsurance(UUID insuranceId, InsuranceDto insurance) {
        return InsuranceApi.super.updateInsurance(insuranceId, insurance);
    }

    @NotNull
    private static InsuranceDto toInsuranceDto(Insurance insurance) {
        var dto = new InsuranceDto(
                insurance.name(),
                InsuranceDto.StatusEnum.valueOf(insurance.insuranceStatus().name()),
                insurance.startDate(),
                insurance.endDate()
        );
        dto.id(insurance.id());
        return dto;
    }
}
