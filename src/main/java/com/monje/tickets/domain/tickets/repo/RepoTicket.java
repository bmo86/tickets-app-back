package com.monje.tickets.domain.tickets.repo;

import com.monje.tickets.domain.tickets.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoTicket extends MongoRepository<Ticket, String> {
}
