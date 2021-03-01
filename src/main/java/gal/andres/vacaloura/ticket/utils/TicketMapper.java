package gal.andres.vacaloura.ticket.utils;

import gal.andres.vacaloura.ticket.model.Status;
import gal.andres.vacaloura.ticket.model.Ticket;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.VoteCounter;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class TicketMapper {

  private TicketMapper() {}

  public static Ticket requestToTicket(NewTicketRequest request) {
    ModelMapper modelMapper = new ModelMapper();
    Ticket ticket = modelMapper.map(request, Ticket.class);
    ticket.setDate(LocalDateTime.now());
    ticket.setStatus(Status.NEW);
    ticket.setVotes(new VoteCounter());
    return ticket;
  }

  public static TicketDTO ticketDTO(Ticket ticket) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(ticket, TicketDTO.class);
  }

  public static Ticket updatedTicket(Ticket ticket, UpdateTicketRequest updateTicketRequest) {
    ticket.setName(updateTicketRequest.getName());
    ticket.setDescription(updateTicketRequest.getDescription());
    ticket.setTags(updateTicketRequest.getTags());
    ticket.setPriority(updateTicketRequest.getPriority());
    ticket.setType(updateTicketRequest.getType());
    ticket.setStepsReproduction(updateTicketRequest.getStepsReproduction());
    ticket.setVersion(updateTicketRequest.getVersion());
    ticket.setStatus(updateTicketRequest.getStatus());
    ticket.setDueDate(updateTicketRequest.getDueDate());
    return ticket;
  }
}
