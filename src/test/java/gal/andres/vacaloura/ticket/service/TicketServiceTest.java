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
class TicketServiceTest {

  private TicketRepository ticketRepository = mock(TicketRepository.class);
  private UserRepository userRepository = mock(UserRepository.class);
  private final TicketService ticketService =
      new TicketServiceImpl(ticketRepository, userRepository);

  private Ticket firstTicket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("First")
        .priority(Priority.HIGH)
        .status(Status.NEW)
        .tags(List.of("new", "bug"))
        .version("1.0")
        .type(TicketType.BUG)
        .build();
  }

  private TicketDTO firstTicketDTO() {
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

  private Ticket secondTicket() {
    return Ticket.Builder.builder()
        .id(2L)
        .name("Second")
        .priority(Priority.LOW)
        .status(Status.ASSIGNED)
        .tags(List.of("old", "enhancement"))
        .version("1.0")
        .type(TicketType.ENHANCEMENT)
        .build();
  }

  private TicketDTO secondTicketDTO() {
    return TicketDTO.Builder.builder()
        .id(2L)
        .name("Second")
        .priority(Priority.LOW)
        .status(Status.ASSIGNED)
        .tags(List.of("old", "enhancement"))
        .version("1.0")
        .type(TicketType.ENHANCEMENT)
        .votes(0)
        .build();
  }

  private UpdateTicketRequest updateTicketRequest() {
    return UpdateTicketRequest.Builder.builder()
        .name("Updated")
        .priority(Priority.VERY_HIGH)
        .status(Status.FIXED)
        .tags(List.of("updated"))
        .version("2.0")
        .type(TicketType.FEATURE_REQUEST)
        .build();
  }

  private NewTicketRequest newTicketRequest() {
    return NewTicketRequest.Builder.builder()
        .name("First")
        .priority(Priority.HIGH)
        .tags(List.of("new", "bug"))
        .version("1.0")
        .type(TicketType.BUG)
        .build();
  }

  private Ticket updatedTicket() {
    return Ticket.Builder.builder()
        .id(1L)
        .name("Updated")
        .priority(Priority.VERY_HIGH)
        .status(Status.FIXED)
        .tags(List.of("updated"))
        .version("2.0")
        .type(TicketType.FEATURE_REQUEST)
        .build();
  }

  private TicketDTO updatedTicketDTO() {
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

  private ApplicationUser applicationUser() {
    return new ApplicationUser("vacaloura_user", "pass", Collections.emptyList());
  }

  @Test
  void shouldFilterTicketsByType() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(firstTicket(), secondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(TicketType.BUG, null, null, null, null);
    Assertions.assertEquals(firstTicketDTO(), tickets.get(0));
  }

  @Test
  void shouldFilterTicketsByPriority() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(firstTicket(), secondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, Priority.LOW, null, null, null);
    Assertions.assertEquals(secondTicketDTO(), tickets.get(0));
  }

  @Test
  void shouldFilterTicketsByTag() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(firstTicket(), secondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, "new", null, null);
    Assertions.assertEquals(firstTicketDTO(), tickets.get(0));
  }

  @Test
  void shouldFilterTicketsByStatus() {
    when(ticketRepository.findAll(Sort.by(Sort.Order.desc("id"))))
        .thenReturn(List.of(firstTicket(), secondTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, Status.ASSIGNED, null);
    Assertions.assertEquals(secondTicketDTO(), tickets.get(0));
  }

  @Test
  void shouldSortTicketsByOneParameter() {
    when(ticketRepository.findAll(ParseUtils.getSortFromQuery("-name")))
        .thenReturn(List.of(secondTicket(), firstTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, null, "-name");
    Assertions.assertEquals(secondTicketDTO(), tickets.get(0));
    Assertions.assertEquals(firstTicketDTO(), tickets.get(1));
  }

  @Test
  void shouldSortTicketByMultipleParameters() {
    when(ticketRepository.findAll(ParseUtils.getSortFromQuery("version,votes")))
        .thenReturn(List.of(secondTicket(), firstTicket()));
    List<TicketDTO> tickets = ticketService.getTickets(null, null, null, null, "version,votes");
    Assertions.assertEquals(secondTicketDTO(), tickets.get(0));
    Assertions.assertEquals(firstTicketDTO(), tickets.get(1));
  }

  @Test
  void shouldGetTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(firstTicket()));
    TicketDTO ticketDTO = ticketService.getTicket(ticketId);
    Assertions.assertEquals(firstTicketDTO(), ticketDTO);
  }

  @Test
  void shouldNotGetTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> ticketService.getTicket(ticketId));
  }

  @Test
  void shouldCreateTicket() {
    when(ticketRepository.save(any())).thenReturn(firstTicket());
    Assertions.assertEquals(firstTicketDTO(), ticketService.createTicket(newTicketRequest()));
  }

  @Test
  void shouldUpdateTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(firstTicket()));
    when(ticketRepository.save(updatedTicket())).thenReturn(updatedTicket());
    Assertions.assertEquals(
        updatedTicketDTO(), ticketService.updateTicket(ticketId, updateTicketRequest()));
  }

  @Test
  void shouldNotUpdateTicketById() {
    long ticketId = 1L;
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> ticketService.updateTicket(ticketId, updateTicketRequest()));
  }

  @Test
  void shouldDeleteTicketById() {
    long ticketId = 1L;
    when(ticketRepository.existsById(ticketId)).thenReturn(true);
    doNothing().when(ticketRepository).deleteById(ticketId);
    ticketService.deleteTicket(ticketId);
    verify(ticketRepository, times(1)).deleteById(eq(ticketId));
  }

  @Test
  void shouldNotDeleteTicketById() {
    long ticketId = 1L;
    when(ticketRepository.existsById(ticketId)).thenReturn(false);
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> ticketService.deleteTicket(ticketId));
  }

  @Test
  void shouldAssignUserToTicket() {
    long ticketId = 1L;
    String username = "vacaloura_user";
    when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(firstTicket()));
    when(userRepository.findByName(username)).thenReturn(applicationUser());
    Assertions.assertEquals(
        applicationUser(), ticketService.assignTicketToUser(ticketId, username).getAssignedTo());
  }

  @Test
  void shouldNotFindTicket() {
    when(ticketRepository.findById(any())).thenReturn(Optional.empty());
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> ticketService.assignTicketToUser(56L, "vacaloura_user"));
  }

  @Test
  void shouldNotFindUser() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.findByName(any())).thenReturn(null);
    Assertions.assertThrows(
        UsernameNotFoundException.class,
        () -> ticketService.assignTicketToUser(56L, "vacaloura_user"));
  }

  @Test
  void shouldVoteForTicket() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.save(any())).thenReturn(firstTicket());
    int votes = ticketService.voteTicket(1L, "username");
    Assertions.assertEquals(1, votes);
  }

  @Test
  void shouldNotVoteTwiceForTicketWithTheSameUser() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.save(any())).thenReturn(firstTicket());
    ticketService.voteTicket(1L, "username");
    int votes = ticketService.voteTicket(1L, "username");
    Assertions.assertEquals(1, votes);
  }

  @Test
  void shouldVoteTwiceForTicketWithTheDifferentUser() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.save(any())).thenReturn(firstTicket());
    ticketService.voteTicket(1L, "username");
    int votes = ticketService.voteTicket(1L, "vacaloura_user");
    Assertions.assertEquals(2, votes);
  }

  @Test
  void shouldDeleteVoteForTicket() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.save(any())).thenReturn(firstTicket());
    ticketService.voteTicket(1L, "username");
    int votes = ticketService.deleteTicketVote(1L, "username");
    Assertions.assertEquals(0, votes);
  }

  @Test
  void shouldNotDeleteVoteNotCastedForTicket() {
    when(ticketRepository.findById(any())).thenReturn(Optional.of(firstTicket()));
    when(userRepository.save(any())).thenReturn(firstTicket());
    ticketService.voteTicket(1L, "vacaloura_user");
    int votes = ticketService.deleteTicketVote(1L, "username");
    Assertions.assertEquals(1, votes);
  }
}
