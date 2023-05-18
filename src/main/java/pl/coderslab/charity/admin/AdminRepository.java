package pl.coderslab.charity.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.Role;
import pl.coderslab.charity.user.User;

import java.util.List;

public interface AdminRepository extends JpaRepository<User, Long> {

    List<User> getAllByRolesContaining(Role adminRole);
}
