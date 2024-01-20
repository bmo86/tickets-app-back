package com.monje.tickets.domain.tickets.dto;

import jakarta.validation.constraints.NotBlank;

public record DataMessage(
        @NotBlank() String message
) {
}
