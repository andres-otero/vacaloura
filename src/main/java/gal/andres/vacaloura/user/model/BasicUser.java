package gal.andres.vacaloura.user.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class BasicUser {
  @Id private String name;
  private String password;

  public BasicUser() {}

  public BasicUser(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "User{" + "name='" + name + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasicUser basicUser = (BasicUser) o;
    return Objects.equals(name, basicUser.name) && Objects.equals(password, basicUser.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, password);
  }
}
