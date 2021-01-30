package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.BasicUser;

public interface UserService {
  /**
   * Creates a new user and saves it in the database
   *
   * @param basicUser User with a username and a password
   * @return {@link ApplicationUser} User created in the database
   */
  public ApplicationUser createUser(BasicUser basicUser);
}
