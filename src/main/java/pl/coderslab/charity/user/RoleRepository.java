package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByRole(String role);
}
