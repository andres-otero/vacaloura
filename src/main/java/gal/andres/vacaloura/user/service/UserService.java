package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.User;

public interface UserService {
  /**
   * Creates a new user and saves it in the database
   *
   * @param user User with a username and a password
   * @return {@link ApplicationUser} User created in the database
   */
  public ApplicationUser createUser(User user);
}
