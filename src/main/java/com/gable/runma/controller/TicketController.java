package com.gable.runma.controller;

import com.gable.runma.model.Ticket;
import com.gable.runma.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ticket")
public class TicketController {
        @Autowired
        private TicketService service;

        //get all tickets
        @GetMapping("/")
         public List<Ticket> findAll(){
        return service.findAll();
    }

        @GetMapping("/{id}")
        Ticket findOne(@PathVariable Integer id) {
            return service.getTicket(id);
        }

        @PostMapping("/")
        public Ticket newTicket(@RequestBody Ticket ticket){
            return service.createTicket(ticket);
        }
}
