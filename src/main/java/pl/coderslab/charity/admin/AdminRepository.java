package pl.coderslab.charity.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.User;

public interface AdminRepository extends JpaRepository<User, Long> {
}
