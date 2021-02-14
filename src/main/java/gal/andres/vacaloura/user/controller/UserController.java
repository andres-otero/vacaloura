package gal.andres.vacaloura.user.controller;

import gal.andres.vacaloura.user.model.BasicUser;
import gal.andres.vacaloura.user.model.Role;
import gal.andres.vacaloura.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<Void> signUp(@RequestBody BasicUser basicUser) {
    userService.createUser(basicUser);
    URI location = URI.create("/users/" + basicUser.getName());
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<Void> deleteUser(@PathVariable String username) {
    userService.deleteUser(username);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{username}/roles")
  public ResponseEntity<Void> addRole(@PathVariable String username, @RequestBody Role role) {
    userService.addRole(username, role);
    URI location = URI.create("/users/" + username);
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/{username}/roles")
  public ResponseEntity<Void> deleteRole(@PathVariable String username, @RequestBody Role role) {
    userService.deleteRole(username, role);
    return ResponseEntity.noContent().build();
  }
}
