package com.monje.tickets.controller;

import com.monje.tickets.domain.tickets.Ticket;
import com.monje.tickets.domain.tickets.dto.DataRegisterTicket;
import com.monje.tickets.domain.tickets.dto.DataResponseTicket;
import com.monje.tickets.domain.tickets.repo.RepoTicket;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
                ticket.getTittle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getProgress(),
                ticket.getStatus(),
                ticket.isActive(),
                ticket.getCreatedAt()
        );

        URI url = uriComponentsBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseTicket>> getTickets(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(repoTicket.findByActiveTrue(pageable).map(DataResponseTicket::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseTicket> getTicket(@PathVariable @Valid String id) {
        Ticket ticket = repoTicket.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found " + id));
        var response = new DataResponseTicket(
                ticket.getId(),
                ticket.getTittle(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getProgress(),
                ticket.getStatus(),
                ticket.isActive(),
                ticket.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponseTicket> deleteTicket(@PathVariable @Valid String id) {
        Ticket ticket = repoTicket.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found " + id));
        ticket.updateActive();
        return ResponseEntity.ok().build();
    }

}
