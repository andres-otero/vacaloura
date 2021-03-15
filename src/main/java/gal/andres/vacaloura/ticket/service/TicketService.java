package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.Priority;
import gal.andres.vacaloura.ticket.model.Status;
import gal.andres.vacaloura.ticket.model.TicketDTO;
import gal.andres.vacaloura.ticket.model.TicketType;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.user.model.ApplicationUser;

import java.util.List;

/** Provides a set of operations related to ticket's management */
public interface TicketService {

  /**
   * Gets the tickets that follow the criteria indicated by the parameters
   *
   * @param ticketType Ticket type to filter the results
   * @param priority Priority to filter the results
   * @param tag Tags to filter the results
   * @param status Status to filter the results
   * @param sort Query with the attributes and their order to sort the list of results
   * @return {@link List<TicketDTO>} Tickets retrieved from the database that fit the criteria
   */
  public List<TicketDTO> getTickets(
      TicketType ticketType, Priority priority, String tag, Status status, String sort);

  /**
   * Gets a ticket with a given id
   *
   * @param ticketId Id of the ticket that will be retrieved
   * @return {@link TicketDTO} Ticket retrieved from the database
   */
  public TicketDTO getTicket(Long ticketId);

  /**
   * Process a ticket request to save a new ticket in the database
   *
   * @param request Ticket request to process
   * @param user User that creates the ticket
   * @return {@link TicketDTO} The created ticket
   */
  public TicketDTO createTicket(NewTicketRequest request, ApplicationUser user);

  /**
   * Updates an existing ticket in the database
   *
   * @param ticketId Id of the ticket that will be updated
   * @param request {@link UpdateTicketRequest} New information to update the ticket
   * @param username Username of the user that made the request
   * @return {@link TicketDTO} The updated ticket
   */
  public TicketDTO updateTicket(Long ticketId, UpdateTicketRequest request, String username);

  /**
   * Assign a user to a ticket
   *
   * @param ticketId Id of the ticket that will be assigned
   * @param username Username of the user assigned
   * @return Ticket with the user assigned
   */
  public TicketDTO assignTicketToUser(Long ticketId, String username);

  /**
   * Delete an existing ticket in the database
   *
   * @param ticketId id of the ticket to be deleted
   */
  public void deleteTicket(Long ticketId);

  /**
   * Add a vote to a ticket
   *
   * @param ticketId Id of the ticket
   * @param username Username of the voter
   * @return Number of ticket votes
   */
  public int voteTicket(Long ticketId, String username);

  /**
   * Deletes a vote from a ticket
   *
   * @param ticketId Id of the ticket
   * @param username Username of the voter
   * @return Number of ticket votes
   */
  public int deleteTicketVote(Long ticketId, String username);
}
