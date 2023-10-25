package com.monje.tickets.domain.tickets;

import com.monje.tickets.domain.tickets.dto.DataRegisterTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tickets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    private String id;
    private String tittle;
    private String description;
    private Number priority;
    private Number progress;
    private String status;
    private boolean active;
    private Date createdAt = new Date();

    public Ticket(DataRegisterTicket data) {
        this.tittle = data.tittle();
        this.description = data.description();
        this.priority = data.priority();
        this.progress = data.progress();
        this.status = data.status();
        this.active = data.active();
    }

    public void updateActive() {
        this.active = !this.active;
    }

}
