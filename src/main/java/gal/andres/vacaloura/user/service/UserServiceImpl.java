package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.Role;
import gal.andres.vacaloura.user.model.User;
import gal.andres.vacaloura.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
  }

  public ApplicationUser createUser(User user) {
    ApplicationUser applicationUser =
        new ApplicationUser(
            user.getName(), bCryptPasswordEncoder.encode(user.getPassword()), Role.BASIC);
    userRepository.save(applicationUser);
    return applicationUser;
  }
}
