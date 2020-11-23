package gal.andres.vacaloura.ticket.utils;

import gal.andres.vacaloura.ticket.model.Status;
import gal.andres.vacaloura.ticket.model.Ticket;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketRequest;

import java.time.LocalDateTime;

public class TicketMapper {

  public static Ticket requestToTicket(TicketRequest request){
    return Ticket.Builder.builder()
        .name(request.getName())
        .description(request.getDescription())
        .type(request.getType())
        .priority(request.getPriority())
        .date(LocalDateTime.now())
        .dueDate(request.getDueDate())
        .tags(request.getTags())
        .status(Status.NEW)
        .version(request.getVersion())
        .votes(0)
        .build();
  }

  public static TicketDTO ticketToDTO(Ticket ticket){
    return TicketDTO.Builder.builder()
        .id(ticket.getId())
        .name(ticket.getName())
        .description(ticket.getDescription())
        .type(ticket.getType())
        .priority(ticket.getPriority())
        .date(ticket.getDate())
        .dueDate(ticket.getDueDate())
        .status(ticket.getStatus())
        .tags(ticket.getTags())
        .version(ticket.getVersion())
        .votes(ticket.getVotes())
        .build();
  }

}
