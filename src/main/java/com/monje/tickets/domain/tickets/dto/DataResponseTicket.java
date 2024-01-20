package com.monje.tickets.domain.tickets.dto;

import com.monje.tickets.domain.tickets.Ticket;

import java.util.Date;

public record DataResponseTicket(
        String id,
        String tittle,
        String description,
        String category,
        Number priority,
        Number progress,
        String status,
        Date createdAt,
        Date updateAt
) {

    public DataResponseTicket(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCategory(),
                ticket.getPriority(),
                ticket.getProgress(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdateAt()
        );
    }


}
