package gal.andres.vacaloura.ticket.controller;

import gal.andres.vacaloura.ticket.model.Priority;
import gal.andres.vacaloura.ticket.model.Status;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketType;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
  private final TicketService ticketService;

  @Autowired
  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @GetMapping
  public ResponseEntity<List<TicketDTO>> getTickets(
      @RequestParam(required = false) TicketType type,
      @RequestParam(required = false) Priority priority,
      @RequestParam(required = false) String tag,
      @RequestParam(required = false) Status status,
      @RequestParam(required = false) String sort) {
    List<TicketDTO> tickets = ticketService.getTickets(type, priority, tag, status, sort);
    return ResponseEntity.ok().body(tickets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TicketDTO> getTicket(@PathVariable long id) {
    TicketDTO ticket = ticketService.getTicket(id);
    return ResponseEntity.ok().body(ticket);
  }

  @PostMapping
  public ResponseEntity<TicketDTO> createTicket(@RequestBody NewTicketRequest newTicketRequest) {
    TicketDTO ticket = ticketService.createTicket(newTicketRequest);
    URI location = URI.create("/tickets/" + ticket.getId());
    return ResponseEntity.created(location).body(ticket);
  }

  @PostMapping("{id}/votes")
  public ResponseEntity<Integer> voteTicket(@PathVariable long id) {
    Integer votes = ticketService.voteTicket(id);
    URI location = URI.create("/tickets/" + id + "/votes");
    return ResponseEntity.created(location).body(votes);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTicket(
      @PathVariable long id, @RequestBody UpdateTicketRequest updateTicketRequest) {
    ticketService.updateTicket(id, updateTicketRequest);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteTicket(@PathVariable long id) {
    ticketService.deleteTicket(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/votes")
  public ResponseEntity deleteVoteTicket(@PathVariable long id) {
    ticketService.deleteTicketVote(id);
    return ResponseEntity.noContent().build();
  }
}
