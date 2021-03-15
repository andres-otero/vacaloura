package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.*;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.repository.TicketRepository;
import gal.andres.vacaloura.ticket.utils.TicketMapper;
import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.repository.UserRepository;
import gal.andres.vacaloura.utils.ParseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;
  private final UserRepository userRepository;

  @Autowired
  public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<TicketDTO> getTickets(
      TicketType ticketType, Priority priority, String tag, Status status, String sort) {
    return ticketRepository.findAll(ParseUtils.getSortFromQuery(sort)).stream()
        .filter(t -> ticketType == null || t.getType() == ticketType)
        .filter(t -> priority == null || t.getPriority() == priority)
        .filter(t -> tag == null || t.getTags().contains(tag))
        .filter(t -> status == null || t.getStatus() == status)
        .map(TicketMapper::ticketDTO)
        .collect(Collectors.toList());
  }

  @Override
  public TicketDTO getTicket(Long ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    return TicketMapper.ticketDTO(ticket);
  }

  @Override
  public TicketDTO createTicket(NewTicketRequest request, ApplicationUser user) {
    Ticket ticket = TicketMapper.requestToTicket(request, user);
    Ticket newTicket = ticketRepository.save(ticket);
    return TicketMapper.ticketDTO(newTicket);
  }

  @Override
  public TicketDTO updateTicket(Long ticketId, UpdateTicketRequest request, String username) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    ticket.setHistory(
        ticket.getHistory() + " Updated by " + username + " with the following changes: ");
    Ticket updatedTicket = TicketMapper.updatedTicket(ticket, request);
    ticketRepository.save(updatedTicket);
    return TicketMapper.ticketDTO(updatedTicket);
  }

  @Override
  public TicketDTO assignTicketToUser(Long ticketId, String username) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    ApplicationUser user = userRepository.findByName(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    ticket.setAssignedTo(user);
    ticketRepository.save(ticket);
    return TicketMapper.ticketDTO(ticket);
  }

  @Override
  public void deleteTicket(Long ticketId) {
    if (ticketRepository.existsById(ticketId)) {
      ticketRepository.deleteById(ticketId);
    } else {
      throw new IllegalArgumentException("Ticket not found");
    }
  }

  @Override
  public int voteTicket(Long ticketId, String username) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    ticket.vote(username);
    ticketRepository.save(ticket);
    return ticket.getVotes();
  }

  @Override
  public int deleteTicketVote(Long ticketId, String username) {
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
    ticket.removeVote(username);
    ticketRepository.save(ticket);
    return ticket.getVotes();
  }
}
