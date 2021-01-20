package gal.andres.vacaloura.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "application_user")
public class ApplicationUser extends User {
  private Role role;

  public ApplicationUser() {}

  public ApplicationUser(String name, String password, Role role) {
    super(name, password);
    this.role = role;
  }

  public Role getRole() {
    return role;
  }

  @Override
  public String toString() {
    return "ApplicationUser{" + "name=" + this.getName() + "role=" + role + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ApplicationUser that = (ApplicationUser) o;
    return Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), role);
  }
}
