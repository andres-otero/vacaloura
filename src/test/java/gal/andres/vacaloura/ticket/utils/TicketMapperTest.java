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
public class TicketMapperTest {

  public NewTicketRequest createNewTicketRequest() {
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

  public Ticket createExpectedTicket() {
    return Ticket.Builder.builder()
        .name("Test bug")
        .description("A test bug")
        .priority(Priority.HIGH)
        .version("1.0")
        .dueDate(LocalDateTime.of(2020, 12, 13, 14, 3))
        .tags(List.of("bug", "test"))
        .type(TicketType.BUG)
        .status(Status.NEW)
        .votes(0)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  public TicketDTO createExpectedTicketDTO() {
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
        .votes(0)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  public Ticket createPlainTicket() {
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
        .votes(0)
        .stepsReproduction("First step: Create the bug")
        .build();
  }

  public UpdateTicketRequest createUpdateTicketRequest(){
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

  public Ticket createExpectedUpdatedTicket(){
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
  public void testNewRequestToTicket() {
    Ticket ticket = TicketMapper.requestToTicket(createNewTicketRequest());
    Ticket expectedTicket = createExpectedTicket();
    Assertions.assertEquals(expectedTicket.getName(), ticket.getName());
    Assertions.assertEquals(expectedTicket.getDescription(), ticket.getDescription());
    Assertions.assertEquals(expectedTicket.getPriority(), ticket.getPriority());
    Assertions.assertEquals(expectedTicket.getStatus(), ticket.getStatus());
    Assertions.assertEquals(expectedTicket.getDueDate(), ticket.getDueDate());
    Assertions.assertEquals(expectedTicket.getType(), ticket.getType());
    Assertions.assertEquals(expectedTicket.getTags(), ticket.getTags());
    Assertions.assertEquals(expectedTicket.getVersion(), ticket.getVersion());
    Assertions.assertEquals(expectedTicket.getVotes(), ticket.getVotes());
    Assertions.assertEquals(expectedTicket.getStepsReproduction(),ticket.getStepsReproduction());
    Assertions.assertNull(ticket.getId());
    Assertions.assertNotNull(ticket.getDate());
  }

  @Test
  public void testTicketToDTO() {
    TicketDTO ticketDTO = TicketMapper.ticketToDTO(createPlainTicket());
    Assertions.assertEquals(createExpectedTicketDTO(), ticketDTO);
  }

  @Test
  public void testUpdateTicket(){
    Ticket updatedTicket = TicketMapper.updateTicket(createPlainTicket(),createUpdateTicketRequest());
    Assertions.assertEquals(createExpectedUpdatedTicket(),updatedTicket);
  }
}
