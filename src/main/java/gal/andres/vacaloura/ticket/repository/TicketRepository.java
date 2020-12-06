package gal.andres.vacaloura.ticket.repository;

import gal.andres.vacaloura.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  public Ticket save(Ticket ticket);

}
