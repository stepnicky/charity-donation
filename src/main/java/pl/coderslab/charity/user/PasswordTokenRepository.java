package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken getByToken(String token);
}
