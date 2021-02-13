package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.ticket.model.*;
import gal.andres.vacaloura.ticket.model.request.NewTicketRequest;
import gal.andres.vacaloura.ticket.model.request.UpdateTicketRequest;
import gal.andres.vacaloura.ticket.repository.TicketRepository;
import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.repository.UserRepository;
import gal.andres.vacaloura.utils.ParseUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketServiceTest {

  private TicketRepository ticketRepository = mock(TicketRepository.class);
  private UserRepository userRepository = mock(UserRepository.class);
  private final TicketService ticketService =
      new TicketServiceImpl(ticketRepository, userRepository);

  private Ticket getFirstTicket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("First")
        .priority(Priority.HIGH)
        .status(Status.NEW)
        .tags(List.of("new", "bug"))
        .version("1.0")
        .votes(0)
        .type(TicketType.BUG)
        .build();
  }

  private TicketDTO getFirstTicketDTO() {
    return TicketDTO.Builder.builder()
        .id(1L)
        .name("First")
        .priority(Priority.HIGH)
        .status(Status.NEW)
        .tags(List.of("new", "bug"))
        .version("1.0")
        .votes(0)
        .type(TicketType.BUG)
        .build();
  }

  private Ticket getSecondTicket() {
    return Ticket.Builder.builder()
        .id(2L)
        .name("Second")
        .priority(Priority.LOW)
        .status(Status.ASSIGNED)
        .tags(List.of("old", "enhancement"))
        .version("1.0")
        .votes(1)
        .type(TicketType.ENHANCEMENT)
        .build();
  }

  private TicketDTO getSecondTicketDTO() {
    return TicketDTO.Builder.builder()
        .id(2L)
        .name("Second")
        .priority(Priority.LOW)
        .status(Status.ASSIGNED)
        .tags(List.of("old", "enhancement"))
        .version("1.0")
        .votes(1)
        .type(TicketType.ENHANCEMENT)
        .build();
  }

  private UpdateTicketRequest getUpdateTicketRequest() {
    return UpdateTicketRequest.Builder.builder()
        .name("Updated")
        .priority(Priority.VERY_HIGH)
        .status(Status.FIXED)
        .tags(List.of("updated"))
        .version("2.0")
        .type(TicketType.FEATURE_REQUEST)
        .build();
  }

  private NewTicketRequest getNewTicketRequest() {
    return NewTicketRequest.Builder.builder()
        .name("First")
        .priority(Priority.HIGH)
        .tags(List.of("new", "bug"))
        .version("1.0")
        .type(TicketType.BUG)
        .build();
  }

  private Ticket getUpdatedTicket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("Updated")
        .priority(Priority.VERY_HIGH)
        .status(Status.FIXED)
        .tags(List.of("updated"))
        .version("2.0")
        .votes(0)
        .type(TicketType.FEATURE_REQUEST)
        .build();
  }

  private TicketDTO getUpdateTicketDTO() {
    return TicketDTO.Builder.builder()
        .id(1L)
        .name("Updated")
        .priority(Priority.VERY_HIGH)
        .status(Status.FIXED)
        .tags(List.of("updated"))
        .version("2.0")
        .votes(0)
        .type(TicketType.FEATURE_REQUEST)
        .build();
  }

  private ApplicationUser getApplicationUser() {
    return new ApplicationUser("vacaloura_user", "pass", Collections.emptyList());
  }

  @Test
  public void shouldFilterTicketsByType() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(getFirstTicket(), getSecondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(TicketType.BUG, null, null, null, null);
    Assertions.assertEquals(getFirstTicketDTO(), tickets.get(0));
  }

  @Test
  public void shouldFilterTicketsByPriority() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(getFirstTicket(), getSecondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, Priority.LOW, null, null, null);
    Assertions.assertEquals(getSecondTicketDTO(), tickets.get(0));
  }

  @Test
  public void shouldFilterTicketsByTag() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(getFirstTicket(), getSecondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, "new", null, null);
    Assertions.assertEquals(getFirstTicketDTO(), tickets.get(0));
  }

  @Test
  public void shouldFilterTicketsByStatus() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(getFirstTicket(), getSecondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, Status.ASSIGNED, null);
    Assertions.assertEquals(getSecondTicketDTO(), tickets.get(0));
  }

  @Test
  public void shouldSortTicketsByOneParameter() {
    when(ticketRepository.findAll(ParseUtils.getSortFromQuery("-name")))
        .thenReturn(List.of(getSecondTicket(), getFirstTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, null, "-name");
    Assertions.assertEquals(getSecondTicketDTO(), tickets.get(0));
    Assertions.assertEquals(getFirstTicketDTO(), tickets.get(1));
  }

  @Test
  public void shouldSortTicketByMultipleParameters() {
    when(ticketRepository.findAll(ParseUtils.getSortFromQuery("version,votes")))
        .thenReturn(List.of(getSecondTicket(), getFirstTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, null, "version,votes");
    Assertions.assertEquals(getSecondTicketDTO(), tickets.get(0));
    Assertions.assertEquals(getFirstTicketDTO(), tickets.get(1));
  }

  @Test
  public void shouldGetTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(getFirstTicket()));
    TicketDTO ticketDTO = ticketService.getTicket(ticketId);
    Assertions.assertEquals(getFirstTicketDTO(), ticketDTO);
  }

  @Test
  public void shouldNotGetTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> ticketService.getTicket(ticketId));
  }

  @Test
  public void shouldCreateTicket() {
    when(ticketRepository.save(any())).thenReturn(getFirstTicket());
    Assertions.assertEquals(getFirstTicketDTO(), ticketService.createTicket(getNewTicketRequest()));
  }

  @Test
  public void shouldUpdateTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(getFirstTicket()));
    when(ticketRepository.save(getUpdatedTicket())).thenReturn(getUpdatedTicket());
    Assertions.assertEquals(
        getUpdateTicketDTO(), ticketService.updateTicket(ticketId, getUpdateTicketRequest()));
  }

  @Test
  public void shouldNotUpdateTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> ticketService.updateTicket(ticketId, getUpdateTicketRequest()));
  }

  @Test
  public void shouldDeleteTicketById() {
    long ticketId = 1L;
    when(ticketRepository.existsById(ticketId)).thenReturn(true);
    doNothing().when(ticketRepository).deleteById(ticketId);
    ticketService.deleteTicket(ticketId);
    verify(ticketRepository, times(1)).deleteById(eq(ticketId));
  }

  @Test
  public void shouldNotDeleteTicketById() {
    long ticketId = 1L;
    when(ticketRepository.existsById(ticketId)).thenReturn(false);
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> ticketService.deleteTicket(ticketId));
  }

  @Test
  public void shouldAssignUserToTicket() {
    long ticketId = 1L;
    String username = "vacaloura_user";
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(getFirstTicket()));
    when(userRepository.findByName(username)).thenReturn(getApplicationUser());
    Assertions.assertEquals(
        getApplicationUser(), ticketService.assignTicketToUser(ticketId, username).getAssignedTo());
  }

  @Test
  public void shouldNotFindTicket() {
    when(ticketRepository.findById(any())).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> ticketService.assignTicketToUser(56L, "vacaloura_user"));
  }

  @Test
  public void shouldNotFindUser() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(getFirstTicket()));
    when(userRepository.findByName(any())).thenReturn(null);
    Assertions.assertThrows(
        UsernameNotFoundException.class,
        () -> ticketService.assignTicketToUser(56L, "vacaloura_user"));
  }
}
