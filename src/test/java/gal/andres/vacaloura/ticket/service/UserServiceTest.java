package gal.andres.vacaloura.ticket.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.Role;
import gal.andres.vacaloura.user.repository.UserRepository;
import gal.andres.vacaloura.user.service.UserService;
import gal.andres.vacaloura.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
  private UserRepository userRepository = mock(UserRepository.class);
  private UserService userService =
      new UserServiceImpl(userRepository, new BCryptPasswordEncoder());

  private ApplicationUser getUser() {
    return new ApplicationUser("vacaloura_user", "pass", List.of(Role.ROLE_BASIC));
  }

  @Test
  public void shouldDeleteUser() {
    String name = "vacaloura_user";
    when(userRepository.existsByName(name)).thenReturn(true);
    doNothing().when(userRepository).deleteByName(name);
    userService.deleteUser(name);
    verify(userRepository, times(1)).deleteByName(eq(name));
  }

  @Test
  public void shouldNotFoundUserToDelete() {
    String name = "vacaloura_user";
    when(userRepository.existsByName(name)).thenReturn(false);
    Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.deleteUser(name));
  }

  @Test
  public void shouldAddRole() {
    String name = "vacalouraUser";
    when(userRepository.existsByName(name)).thenReturn(true);
    when(userRepository.findByName(name)).thenReturn(getUser());
    List<Role> roles = userService.addRole(name, Role.ROLE_DEVELOPER).getRoles();
    Assertions.assertEquals(List.of(Role.ROLE_BASIC, Role.ROLE_DEVELOPER), roles);
  }

  @Test
  public void shouldNotFoundUserToAddRole() {
    String name = "vacaloura_user";
    when(userRepository.existsByName(name)).thenReturn(false);
    Assertions.assertThrows(
        UsernameNotFoundException.class, () -> userService.addRole(name, Role.ROLE_DEVELOPER));
  }

  @Test
  public void shouldDeleteRole() {
    String name = "vacalouraUser";
    when(userRepository.existsByName(name)).thenReturn(true);
    when(userRepository.findByName(name)).thenReturn(getUser());
    Assertions.assertTrue(userService.deleteRole(name, Role.ROLE_BASIC).getRoles().isEmpty());
  }

  @Test
  public void shouldNotFoundUserToDeleteRole() {
    String name = "vacaloura_user";
    when(userRepository.existsByName(name)).thenReturn(false);
    Assertions.assertThrows(
        UsernameNotFoundException.class, () -> userService.deleteRole(name, Role.ROLE_BASIC));
  }
}
