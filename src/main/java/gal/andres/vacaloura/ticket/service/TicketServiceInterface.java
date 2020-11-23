package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketRequest;

public interface TicketServiceInterface {

  public TicketDTO createTicket(TicketRequest ticket);
}
