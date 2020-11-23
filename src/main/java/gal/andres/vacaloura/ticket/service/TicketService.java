package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.Ticket;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketRequest;
import gal.andres.vacaloura.ticket.repository.TicketRepository;
import gal.andres.vacaloura.ticket.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketService implements TicketServiceInterface {
  private final TicketRepository ticketRepository;

  @Autowired
  public TicketService(TicketRepository ticketRepository){
    this.ticketRepository = ticketRepository;
  }
  
  public TicketDTO createTicket(TicketRequest request) {
    Ticket ticket = TicketMapper.requestToTicket(request);
    ticketRepository.save(ticket);
    return TicketMapper.ticketToDTO(ticket);


  }
}
