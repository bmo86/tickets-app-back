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
    private String title;
    private String description;
    private String category;
    private Number priority;
    private Number progress;
    private String status;
    private Date createdAt = new Date();
    private Date updateAt = new Date();
    public Ticket(DataRegisterTicket data) {
        this.title = data.title();
        this.description = data.description();
        this.category = data.category();
        this.priority = data.priority();
        this.progress = data.progress();
        this.status = data.status();
    }



}
