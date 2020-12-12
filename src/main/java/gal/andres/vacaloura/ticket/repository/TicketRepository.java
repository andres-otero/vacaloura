package gal.andres.vacaloura.ticket.repository;

import gal.andres.vacaloura.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  Ticket save(Ticket ticket);
}
