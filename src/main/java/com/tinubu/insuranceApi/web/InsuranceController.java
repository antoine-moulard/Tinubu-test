package com.tinubu.insuranceApi.web;

import org.openapitools.api.InsuranceApi;
import org.openapitools.model.Insurance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class InsuranceController implements InsuranceApi {
    @Override
    public ResponseEntity<List<Insurance>> getInsurances() {
        return InsuranceApi.super.getInsurances();
    }

    @Override
    public ResponseEntity<Insurance> getInsurance(UUID insuranceId) {
        return InsuranceApi.super.getInsurance(insuranceId);
    }

    @Override
    public ResponseEntity<List<Insurance>> createInsurance(Insurance insurance) {
        return InsuranceApi.super.createInsurance(insurance);
    }

    @Override
    public ResponseEntity<List<Insurance>> updateInsurance(UUID insuranceId, Insurance insurance) {
        return InsuranceApi.super.updateInsurance(insuranceId, insurance);
    }
}
