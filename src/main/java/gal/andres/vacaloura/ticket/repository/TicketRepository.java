package gal.andres.vacaloura.ticket.repository;

import gal.andres.vacaloura.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  Ticket save(Ticket ticket);
}
