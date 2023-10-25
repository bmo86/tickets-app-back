package com.monje.tickets.domain.tickets.dto;

import com.monje.tickets.domain.tickets.Ticket;

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

    public DataResponseTicket(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getTittle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getProgress(),
                ticket.getStatus(),
                ticket.isActive(),
                ticket.getCreatedAt()
        );
    }
}
