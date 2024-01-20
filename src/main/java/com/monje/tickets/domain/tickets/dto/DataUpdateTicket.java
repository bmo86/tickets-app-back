package com.monje.tickets.domain.tickets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTicket(
        @NotNull(message = "id Required") String id,
        @NotBlank(message = "tittle is required") String title,
        @NotBlank(message = "description is required") String description,

        @NotBlank(message = "description is required") String category,
        @NotBlank(message = "priority more to 0") Number priority,
        @NotBlank(message = "priority more to 0") Number progress,
        @NotBlank(message = "status is required") String status
) {
}
