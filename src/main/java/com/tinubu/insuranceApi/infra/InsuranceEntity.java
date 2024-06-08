package com.tinubu.insuranceApi.infra;

import com.tinubu.insuranceApi.domain.Insurance;
import com.tinubu.insuranceApi.domain.InsuranceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private InsuranceStatus insuranceStatus;

    @NotNull
    private OffsetDateTime startDate;

    @NotNull
    private OffsetDateTime endDate;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public InsuranceEntity(String name,
                           InsuranceStatus insuranceStatus,
                           OffsetDateTime startDate,
                           OffsetDateTime endDate) {
        this.name = name;
        this.insuranceStatus = insuranceStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Insurance toDomain() {
        return new Insurance(this.getId(),
                this.getName(),
                this.getInsuranceStatus(),
                this.getStartDate(),
                this.getEndDate(),
                new Insurance.Technical(this.getCreatedAt(), this.getUpdatedAt())
        );
    }
}
