package com.monje.tickets.domain.tickets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTicket(
        @NotBlank(message = "tittle is required") String tittle,
        @NotBlank(message = "description is required")String description,
        @NotBlank(message = "priority more to 0") Number priority,
        @NotBlank(message = "priority more to 0") Number progress,
        @NotBlank(message = "status is required") String status,
        @NotNull(message = "the field is missing 'active'") boolean active

) {
}
