package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.*;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.repository.TicketRepository;
import gal.andres.vacaloura.ticket.utils.TicketMapper;
import gal.andres.vacaloura.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;

  @Autowired
  public TicketServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public List<TicketDTO> getTickets(
      TicketType ticketType, Priority priority, String tag, Status status, String sort) {
    return ticketRepository.findAll(ParseUtils.getSortFromQuery(sort)).stream()
        .filter(t -> ticketType == null || t.getType() == ticketType)
        .filter(t -> priority == null || t.getPriority() == priority)
        .filter(t -> tag == null || t.getTags().contains(tag))
        .filter(t -> status == null || t.getStatus() == status)
        .map(TicketMapper::ticketToDTO)
        .collect(Collectors.toList());
  }

  @Override
  public TicketDTO getTicket(Long ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    return TicketMapper.ticketToDTO(ticket);
  }

  @Override
  public TicketDTO createTicket(NewTicketRequest request) {
    Ticket ticket = TicketMapper.requestToTicket(request);
    ticketRepository.save(ticket);
    return TicketMapper.ticketToDTO(ticket);
  }

  @Override
  public void updateTicket(Long ticketId, UpdateTicketRequest request) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    ticket.update(request);
    ticketRepository.save(ticket);
  }

  @Override
  public void deleteTicket(Long ticketId) {
    if (ticketRepository.existsById(ticketId)) {
      ticketRepository.deleteById(ticketId);
    } else {
      throw new IllegalArgumentException("Ticket not found");
    }
  }
}
