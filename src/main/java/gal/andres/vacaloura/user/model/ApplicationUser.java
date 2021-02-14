package gal.andres.vacaloura.user.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "application_user")
public class ApplicationUser extends BasicUser {
  @ElementCollection private List<Role> roles;

  public ApplicationUser() {}

  public ApplicationUser(String name, String password, List<Role> roles) {
    super(name, password);
    this.roles = roles;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "ApplicationUser{" + "name=" + this.getName() + "roles=" + roles + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ApplicationUser that = (ApplicationUser) o;
    return roles == that.roles;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), roles);
  }
}
