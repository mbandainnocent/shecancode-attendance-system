package com.shecancode.attendence.registration.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cohort creation payload")
public class CohortRequestDao {
    @NotBlank(message = "Cohort number cannot be blank")
    @Schema(example = "Cohort-11", description = "Unique cohort identifier")
    private String cohortNumber;

    @NotNull(message = "programId is required")
    @Schema(description = "ID of the existing program this cohort belongs to")
    private UUID programId;

    @Schema(example = "2026-03-01")
    private LocalDate startDate;

    @Schema(example = "2026-09-01")
    private LocalDate endDate;

    @Schema(example = "true", description = "Whether students of this cohort are eligible for a stipend. "
            + "Required when creating a cohort; on update, omit it to keep the current value.")
    private Boolean stipendEligible;
    @jakarta.validation.constraints.DecimalMin(value = "0", inclusive = false,
            message = "stipendAmount must be positive")
    @jakarta.validation.constraints.Digits(integer = 17, fraction = 2)
    @Schema(example = "50000.00", description = "Stipend amount per student. Required and positive when stipendEligible is true. "
            + "Omit on update to keep the current amount; cleared when eligibility is disabled.")
    private BigDecimal stipendAmount;

}
