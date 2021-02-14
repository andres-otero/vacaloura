package gal.andres.vacaloura.user.repository;

import gal.andres.vacaloura.user.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<ApplicationUser, String> {
  @Query("SELECT a FROM ApplicationUser a JOIN FETCH a.roles WHERE a.name = (:name)")
  public ApplicationUser findByName(String name);

  public boolean existsByName(String name);

  public void deleteByName(String name);
}
