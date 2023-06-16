package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenRepository extends JpaRepository<AccountActivationToken, Long> {

    AccountActivationToken getByToken(String token);
}
