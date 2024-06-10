package com.tinubu.insuranceApi.web;

import com.tinubu.insuranceApi.domain.CommandInsurance;
import com.tinubu.insuranceApi.domain.QueryInsurance;
import com.tinubu.insuranceApi.domain.exception.InsuranceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.tinubu.insuranceApi.web.InsuranceMapper.toInsuranceDto;

@RestController
@RequiredArgsConstructor
public class InsuranceController implements InsuranceApi {

    private final QueryInsurance queryInsurance;
    private final CommandInsurance commandInsurance;

    @Override
    public ResponseEntity<List<InsuranceDto>> getInsurances() {
        var insurances = queryInsurance.all().stream()
                .map(InsuranceMapper::toInsuranceDto)
                .toList();
        return ResponseEntity.ok(insurances);
    }

    @Override
    public ResponseEntity<InsuranceDto> getInsurance(UUID insuranceId) {
        var insurance = this.queryInsurance.with(insuranceId);
        return ResponseEntity.ok(toInsuranceDto(insurance));
    }

    @Override
    public ResponseEntity<CreateInsurance201ResponseDto> createInsurance(InsuranceDto insuranceDto) {
        var createCommand = InsuranceMapper.buildCreateCommand(insuranceDto);
        var id = this.commandInsurance.create(createCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateInsurance201ResponseDto().id(id));
    }

    @Override
    public ResponseEntity<Void> updateInsurance(UUID id, InsuranceDto insuranceDto) {
        var updateCommand = InsuranceMapper.buildUpdateCommand(id, insuranceDto);
        this.commandInsurance.update(updateCommand);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(InsuranceNotFoundException.class)
    public void notFound() {
    }
}
