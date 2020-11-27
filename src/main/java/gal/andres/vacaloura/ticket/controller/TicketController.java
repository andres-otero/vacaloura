package gal.andres.vacaloura.ticket.controller;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketRequest;
import gal.andres.vacaloura.ticket.service.TicketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
@RequestMapping("/tickets")
@Api(value = "ticket-controller", )
public class TicketController {
  private final TicketService ticketService;

  @Autowired
  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @PostMapping
  public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketRequest ticketRequest) {
    TicketDTO ticket = ticketService.createTicket(ticketRequest);
    URI location = URI.create("/tickets/" + ticket.getId());
    ResponseEntity response = ResponseEntity.created(location).body(ticket);
    return response;
  }
}
