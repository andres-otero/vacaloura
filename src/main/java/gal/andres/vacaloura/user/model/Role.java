package gal.andres.vacaloura.user.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_BASIC,
  ROLE_DEVELOPER,
  ROLE_ADMIN;

  @Override
  public String getAuthority(){
    return name();
  }
}
