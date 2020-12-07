package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;

/** Provides a set of operations related to ticket's management */
public interface TicketService {

  /**
   * Process a ticket request to save a new ticket in the database
   *
   * @param request Ticket request to process
   * @return {@link TicketDTO} The created ticket
   */
  public TicketDTO createTicket(NewTicketRequest request);

  /**
   * Updates an existing ticket in the database
   * @param ticketId Id of the ticket that will be updated
   * @param request {@link UpdateTicketRequest} New information to update the ticket
   */
  public void updateTicket(Long ticketId, UpdateTicketRequest request);
}
