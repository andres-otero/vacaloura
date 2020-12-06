package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.Ticket;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.repository.TicketRepository;
import gal.andres.vacaloura.ticket.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;

  @Autowired
  public TicketServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public TicketDTO createTicket(NewTicketRequest request) {
    Ticket ticket = TicketMapper.requestToTicket(request);
    ticketRepository.save(ticket);
    return TicketMapper.ticketToDTO(ticket);
  }

  @Override
  public void updateTicket(Long ticketId, UpdateTicketRequest request) {
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    if (ticket.isPresent()) {
      this.updateTicketFields(ticket.get(), request);
      ticketRepository.save(ticket.get());
    } else {
      throw new IllegalArgumentException("Ticket not found");
    }
  }

  public void updateTicketFields(Ticket ticket, UpdateTicketRequest request) {
    ticket.setName(request.getName());
    ticket.setDescription(request.getDescription());
    ticket.setStatus(request.getStatus());
    ticket.setPriority(request.getPriority());
    ticket.setTags(request.getTags());
    ticket.setDueDate(request.getDueDate());
    ticket.setType(request.getType());
    ticket.setVersion(request.getVersion());
  }
}
