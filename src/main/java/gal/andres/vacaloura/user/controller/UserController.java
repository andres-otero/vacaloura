package gal.andres.vacaloura.user.controller;

import gal.andres.vacaloura.user.model.BasicUser;
import gal.andres.vacaloura.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
