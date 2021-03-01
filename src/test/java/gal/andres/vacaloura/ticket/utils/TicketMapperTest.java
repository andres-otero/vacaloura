package gal.andres.vacaloura.ticket.utils;

import gal.andres.vacaloura.ticket.model.*;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TicketMapperTest {

  private NewTicketRequest newTicketRequest() {
    return NewTicketRequest.Builder.builder()
        .name("Test bug")
        .description("A test bug")
        .priority(Priority.HIGH)
        .version("1.0")
        .dueDate(LocalDateTime.of(2020, 12, 13, 14, 3))
        .tags(List.of("bug", "test"))
        .type(TicketType.BUG)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  private Ticket expectedTicket() {
    return Ticket.Builder.builder()
        .name("Test bug")
        .description("A test bug")
        .priority(Priority.HIGH)
        .version("1.0")
        .dueDate(LocalDateTime.of(2020, 12, 13, 14, 3))
        .tags(List.of("bug", "test"))
        .type(TicketType.BUG)
        .status(Status.NEW)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  private TicketDTO expectedTicketDTO() {
    return TicketDTO.Builder.builder()
        .id(1L)
        .name("Test bug")
        .description("A test bug")
        .priority(Priority.HIGH)
        .version("1.0")
        .date(LocalDateTime.of(2020, 12, 13, 14, 3))
        .dueDate(LocalDateTime.of(2020, 12, 13, 14, 3))
        .tags(List.of("bug", "test"))
        .type(TicketType.BUG)
        .status(Status.NEW)
        .stepsReproduction("First step: Create the bug")
        .votes(0)
        .build();
  }

  private Ticket ticket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("Test bug")
        .description("A test bug")
        .priority(Priority.HIGH)
        .version("1.0")
        .date(LocalDateTime.of(2020, 12, 13, 14, 3))
        .dueDate(LocalDateTime.of(2020, 12, 13, 14, 3))
        .tags(List.of("bug", "test"))
        .type(TicketType.BUG)
        .status(Status.NEW)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  private UpdateTicketRequest updateTicketRequest() {
    return UpdateTicketRequest.Builder.builder()
        .name("Update ticket")
        .description("updatedTicket")
        .status(Status.FIXED)
        .stepsReproduction("reproduction steps")
        .dueDate(LocalDateTime.of(2020, 12, 15, 14, 3))
        .priority(Priority.MEDIUM)
        .version("2.0")
        .type(TicketType.ENHANCEMENT)
        .tags(List.of("updated", "enhancement"))
        .build();
  }

  private Ticket updatedTicket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("Update ticket")
        .description("updatedTicket")
        .status(Status.FIXED)
        .stepsReproduction("reproduction steps")
        .dueDate(LocalDateTime.of(2020, 12, 15, 14, 3))
        .priority(Priority.MEDIUM)
        .version("2.0")
        .type(TicketType.ENHANCEMENT)
        .tags(List.of("updated", "enhancement"))
        .votes(0)
        .date(LocalDateTime.of(2020, 12, 13, 14, 3))
        .build();
  }

  @Test
  void shouldMapNewTicketRequestToTicket() {
    Ticket ticket = TicketMapper.requestToTicket(newTicketRequest());
    Ticket expectedTicket = expectedTicket();
    Assertions.assertEquals(expectedTicket.getName(), ticket.getName());
    Assertions.assertEquals(expectedTicket.getDescription(), ticket.getDescription());
    Assertions.assertEquals(expectedTicket.getPriority(), ticket.getPriority());
    Assertions.assertEquals(expectedTicket.getStatus(), ticket.getStatus());
    Assertions.assertEquals(expectedTicket.getDueDate(), ticket.getDueDate());
    Assertions.assertEquals(expectedTicket.getType(), ticket.getType());
    Assertions.assertEquals(expectedTicket.getTags(), ticket.getTags());
    Assertions.assertEquals(expectedTicket.getVersion(), ticket.getVersion());
    Assertions.assertEquals(expectedTicket.getVotes(), ticket.getVotes());
    Assertions.assertEquals(expectedTicket.getStepsReproduction(), ticket.getStepsReproduction());
    Assertions.assertNull(ticket.getId());
    Assertions.assertNotNull(ticket.getDate());
  }

  @Test
  void shouldMapTicketToTicketDTO() {
    TicketDTO ticketDTO = TicketMapper.ticketDTO(ticket());
    Assertions.assertEquals(expectedTicketDTO(), ticketDTO);
  }

  @Test
  void shouldMapUpdateTicketRequestToTicket() {
    Ticket updatedTicket = TicketMapper.updatedTicket(ticket(), updateTicketRequest());
    Assertions.assertEquals(updatedTicket(), updatedTicket);
  }
}
