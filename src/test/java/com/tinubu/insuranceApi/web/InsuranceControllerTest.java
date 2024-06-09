package com.tinubu.insuranceApi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinubu.insuranceApi.domain.CommandInsurance;
import com.tinubu.insuranceApi.domain.QueryInsurance;
import com.tinubu.insuranceApi.domain.models.Insurance;
import com.tinubu.insuranceApi.domain.models.InsuranceStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.*;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class InsuranceControllerTest {
    private static final String DEFAULT_UUID = "a3bb508d-2629-4efc-822f-7d9201c6fdfe";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private InsuranceController insuranceController;

    @MockBean
    private QueryInsurance queryInsurance;
    @MockBean
    private CommandInsurance commandInsurance;

    @Test
    void get_all_should_correctly_return_insurances() throws Exception {
        mockMvc.perform(
                get("/insurance")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void get_should_correctly_return_insurance() throws Exception {
        Mockito.when(queryInsurance.with(UUID.fromString(DEFAULT_UUID))).thenReturn(buildDefaultInsurance());

        mockMvc.perform(
                get("/insurance/%s".formatted(DEFAULT_UUID))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void post_should_create_correctly_insurance() throws Exception {
        mockMvc.perform(
                post("/insurance")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "name": "test",
                                  "status": "active",
                                  "startDate": "2024-06-09T00:00:00.000Z",
                                  "endDate": "2024-06-09T00:00:00.000Z"
                                }
                                """)
        ).andExpect(status().isCreated());
    }

    @Test
    void update_should_create_correctly_insurance() throws Exception {
        mockMvc.perform(
                put("/insurance/%s".formatted(UUID.fromString(DEFAULT_UUID)))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                  "name": "test",
                                  "status": "inactive",
                                  "startDate": "2024-06-09T00:00:00.000Z",
                                  "endDate": "2024-06-09T00:00:00.000Z"
                                }
                                """)
        ).andExpect(status().isOk());
    }

    private Insurance buildDefaultInsurance() {
        var offset = ZoneOffset.of("+02:00");
        var startDate = OffsetDateTime.of(
                LocalDateTime.of(2024, Month.JANUARY, 15, 0, 0, 0),
                offset
        );
        var endDate = OffsetDateTime.of(
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0, 0),
                offset
        );

        return new Insurance(
                UUID.fromString(DEFAULT_UUID),
                "test",
                InsuranceStatus.ACTIVE,
                startDate,
                endDate,
                new Insurance.Technical(Instant.now(), Instant.now())
        );
    }
}
