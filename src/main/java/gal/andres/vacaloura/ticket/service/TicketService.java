package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketRequest;

public interface TicketService {

  /**
   * Process a ticket request to save a new ticket in the database
   *
   * @param request Ticket request to process
   * @return The ticket created
   */
  public TicketDTO createTicket(TicketRequest request);
}
