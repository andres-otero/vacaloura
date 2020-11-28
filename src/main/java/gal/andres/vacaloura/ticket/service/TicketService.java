package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;

/**
 * Provides a set of operations related to ticket's management
 */
public interface TicketService {

  /**
   * Process a ticket request to save a new ticket in the database
   *
   * @param request Ticket request to process
   * @return {@link TicketDTO} The created ticket
   */
  public TicketDTO createTicket(NewTicketRequest request);
}
