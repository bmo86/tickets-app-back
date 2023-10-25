package com.monje.tickets.domain.tickets.repo;

import com.monje.tickets.domain.tickets.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoTicket extends MongoRepository<Ticket, String> {
    Page<Ticket> findByActiveTrue(Pageable pageable);
}
