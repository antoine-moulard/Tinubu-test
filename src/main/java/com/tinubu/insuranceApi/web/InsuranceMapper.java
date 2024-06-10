package com.tinubu.insuranceApi.web;

import com.tinubu.insuranceApi.domain.models.CreateInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;
import com.tinubu.insuranceApi.domain.models.InsuranceStatus;
import com.tinubu.insuranceApi.domain.models.UpdateInsurance;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public interface InsuranceMapper {

    @NotNull
    static InsuranceDto toInsuranceDto(Insurance insurance) {
        var dto = new InsuranceDto(
                insurance.name(),
                InsuranceDto.StatusEnum.valueOf(insurance.insuranceStatus().name()),
                insurance.startDate(),
                insurance.endDate()
        );
        dto.id(insurance.id());
        dto.createdAt(OffsetDateTime.ofInstant(insurance.technical().createdAt(), ZoneId.of("+02:00")));
        dto.lastUpdatedAt(OffsetDateTime.ofInstant(insurance.technical().lastUpdateAt(), ZoneId.of("+02:00")));
        return dto;
    }

    static CreateInsurance buildCreateCommand(InsuranceDto insuranceDto) {
        return new CreateInsurance(
                insuranceDto.getName(),
                InsuranceStatus.valueOf(insuranceDto.getStatus().name()),
                insuranceDto.getStartDate(),
                insuranceDto.getEndDate()
        );
    }

    static UpdateInsurance buildUpdateCommand(UUID id, InsuranceDto insuranceDto) {
        return new UpdateInsurance(
                id,
                insuranceDto.getName(),
                InsuranceStatus.valueOf(insuranceDto.getStatus().name()),
                insuranceDto.getStartDate(),
                insuranceDto.getEndDate()
        );
    }

}
