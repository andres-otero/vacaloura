package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.Role;
import gal.andres.vacaloura.user.model.BasicUser;
import gal.andres.vacaloura.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public ApplicationUser createUser(BasicUser basicUser) {
    ApplicationUser applicationUser =
        new ApplicationUser(
            basicUser.getName(), bCryptPasswordEncoder.encode(basicUser.getPassword()), List.of(Role.ROLE_BASIC));
    userRepository.save(applicationUser);
    return applicationUser;
  }
}
