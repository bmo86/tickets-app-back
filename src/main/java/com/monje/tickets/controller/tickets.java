package com.monje.tickets.controller;

import com.mongodb.lang.NonNull;
import com.monje.tickets.domain.tickets.Ticket;
import com.monje.tickets.domain.tickets.dto.DataMessage;
import com.monje.tickets.domain.tickets.dto.DataRegisterTicket;
import com.monje.tickets.domain.tickets.dto.DataResponseTicket;
import com.monje.tickets.domain.tickets.dto.DataUpdateTicket;
import com.monje.tickets.domain.tickets.repo.RepoTicket;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tickets")
public class tickets {
    @Autowired
    private RepoTicket repoTicket;

    @PostMapping
    public ResponseEntity<DataResponseTicket> createTicket(@RequestBody @Valid DataRegisterTicket data, UriComponentsBuilder uriComponentsBuilder) {
        Ticket ticket = repoTicket.save(new Ticket(data));
        DataResponseTicket response = new DataResponseTicket(
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

        URI url = uriComponentsBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseTicket>> getTickets(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(repoTicket.findAll(pageable).map(DataResponseTicket::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseTicket> getTicket(@PathVariable @Valid String id) {
        Ticket ticket = repoTicket.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found " + id));
        var response = new DataResponseTicket(
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
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataMessage> deleteTicket(@PathVariable @Valid String id) {

        if (repoTicket.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repoTicket.deleteById(id);
        return ResponseEntity.ok(new DataMessage("Delete Ticket"));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseTicket> updateTicket(@RequestBody @Valid DataUpdateTicket data, @PathVariable @Valid String id) {
        Optional<Ticket> ticket = repoTicket.findById(id);
        ticket.get().updateTicket(data);
        repoTicket.save(ticket.get());
        return ResponseEntity.ok(new DataResponseTicket(
                ticket.get().getId(),
                ticket.get().getTitle(),
                ticket.get().getDescription(),
                ticket.get().getCategory(),
                ticket.get().getPriority(),
                ticket.get().getProgress(),
                ticket.get().getStatus(),
                ticket.get().getCreatedAt(),
                ticket.get().getUpdateAt()
        ));
    }


}
