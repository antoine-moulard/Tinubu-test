package com.tinubu.insuranceApi.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.*;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InsuranceTest {

    @ParameterizedTest
    @MethodSource
    void should_throw_when_data_is_missing(UUID uuid,
                                           String name,
                                           InsuranceStatus insuranceStatus,
                                           OffsetDateTime startTime,
                                           OffsetDateTime endDate,
                                           Insurance.Technical technical) {
        assertThatThrownBy(
                () -> new Insurance(uuid, name, insuranceStatus, startTime, endDate, technical)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> should_throw_when_data_is_missing() {
        var uuid = UUID.fromString("75553d4f-7e26-4f90-9860-e141fee836b3");
        var offset = ZoneOffset.of("+02:00");
        var startDate = OffsetDateTime.of(
                LocalDateTime.of(2024, Month.JANUARY, 15, 0, 0, 0),
                offset
        );
        var endDate = OffsetDateTime.of(
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0, 0),
                offset
        );
        var technical = new Insurance.Technical(Instant.now(), Instant.now());
        return Stream.of(
                Arguments.of(null, "test", InsuranceStatus.ACTIVE, startDate, endDate, technical),
                Arguments.of(uuid, null, InsuranceStatus.ACTIVE, startDate, endDate, technical),
                Arguments.of(uuid, "test", null, startDate, endDate, technical),
                Arguments.of(uuid, "test", InsuranceStatus.ACTIVE, null, endDate, technical),
                Arguments.of(uuid, "test", InsuranceStatus.ACTIVE, startDate, null, technical),
                Arguments.of(uuid, "test", InsuranceStatus.ACTIVE, startDate, endDate, null)
        );
    }
}
