package gal.andres.vacaloura.ticket.controller;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tickets")
public class TicketController {
  private final TicketService ticketService;

  @Autowired
  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @PostMapping
  public ResponseEntity<TicketDTO> createTicket(@RequestBody NewTicketRequest newTicketRequest) {
    TicketDTO ticket = ticketService.createTicket(newTicketRequest);
    URI location = URI.create("/tickets/" + ticket.getId());
    ResponseEntity response = ResponseEntity.created(location).body(ticket);
    return response;
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTicket(
      @PathVariable("id") Long id, UpdateTicketRequest updateTicketRequest) {
    ticketService.updateTicket(id, updateTicketRequest);
    return ResponseEntity.noContent().build();
  }
}
