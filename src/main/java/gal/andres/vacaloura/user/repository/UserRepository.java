package gal.andres.vacaloura.user.repository;

import gal.andres.vacaloura.user.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, String> {}
