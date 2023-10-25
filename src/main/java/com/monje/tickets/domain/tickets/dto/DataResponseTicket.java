package com.monje.tickets.domain.tickets.dto;

import java.util.Date;

public record DataResponseTicket(
        String id,
        String tittle,
        String description,
        Number priority,
        Number progress,
        String status,
        boolean active,
        Date createdAt
) {
}
