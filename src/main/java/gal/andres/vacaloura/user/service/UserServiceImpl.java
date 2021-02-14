package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.BasicUser;
import gal.andres.vacaloura.user.model.Role;
import gal.andres.vacaloura.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public ApplicationUser createUser(BasicUser basicUser) {
    List<Role> roles = new ArrayList<>();
    roles.add(Role.ROLE_BASIC);
    ApplicationUser applicationUser =
        new ApplicationUser(
            basicUser.getName(), bCryptPasswordEncoder.encode(basicUser.getPassword()), roles);
    userRepository.save(applicationUser);
    return applicationUser;
  }

  @Override
  public void deleteUser(String username) {
    if (userRepository.existsByName(username)) {
      userRepository.deleteByName(username);
    } else {
      throw new UsernameNotFoundException(username);
    }
  }

  @Override
  public ApplicationUser addRole(String username, Role role) {
    if (userRepository.existsByName(username)) {
      ApplicationUser user = userRepository.findByName(username);
      List<Role> roles = new ArrayList<>(user.getRoles());
      roles.add(role);
      user.setRoles(roles);
      userRepository.save(user);
      return user;
    } else {
      throw new UsernameNotFoundException(username);
    }
  }

  @Override
  public ApplicationUser deleteRole(String username, Role role) {
    if (userRepository.existsByName(username)) {
      ApplicationUser user = userRepository.findByName(username);
      List<Role> roles = new ArrayList<>(user.getRoles());
      roles.remove(role);
      user.setRoles(roles);
      userRepository.save(user);
      return user;
    } else {
      throw new UsernameNotFoundException(username);
    }
  }
}
