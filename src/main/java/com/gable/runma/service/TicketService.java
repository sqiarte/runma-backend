package com.gable.runma.service;

import com.gable.runma.exception.ResourceNotFoundException;
import com.gable.runma.model.RaceType;
import com.gable.runma.model.Ticket;
import com.gable.runma.model.User;
import com.gable.runma.repository.TicketRepository;
import com.gable.runma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    //get ticket by ticketId
    public Ticket getTicket(Integer id) {
        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket with id: " + id + " does not exist"));
        return ResponseEntity.ok(ticket).getBody();
    }

    //crete new ticket
    public Ticket createTicket(Ticket ticket) {
        ticketRepo.save(ticket);
        return ticket;
    }
}
