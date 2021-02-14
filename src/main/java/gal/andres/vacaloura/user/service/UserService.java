package gal.andres.vacaloura.user.service;

import gal.andres.vacaloura.user.model.ApplicationUser;
import gal.andres.vacaloura.user.model.BasicUser;
import gal.andres.vacaloura.user.model.Role;

public interface UserService {
  /**
   * Creates a new user and saves it in the database
   *
   * @param basicUser User with a username and a password
   * @return {@link ApplicationUser} User created in the database
   */
  public ApplicationUser createUser(BasicUser basicUser);

  /**
   * Delete an user from the database
   *
   * @param username Name of the user to be deleted
   */
  public void deleteUser(String username);

  /**
   * Add a new role to an existing user
   *
   * @param username Name of the user
   * @param role Role to be added
   * @return User with the new role
   */
  public ApplicationUser addRole(String username, Role role);

  /**
   * Delete a role from an existing user
   *
   * @param username Name of the user
   * @param role Role to be deleted
   * @return User without the role
   */
  public ApplicationUser deleteRole(String username, Role role);
}
