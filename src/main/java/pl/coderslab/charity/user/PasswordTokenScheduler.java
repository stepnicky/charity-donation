package pl.coderslab.charity.user;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PasswordTokenScheduler {

    private final PasswordTokenRepository passwordTokenRepository;

    public PasswordTokenScheduler(PasswordTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @Scheduled(fixedRate = 60_000)
    public void checkExpiryDates() {
        LocalDateTime now = LocalDateTime.now();
        List<PasswordResetToken> tokens = passwordTokenRepository.findAll();
        tokens.stream().forEach(t -> {
            if (t.getExpiryDate().isBefore(now)) {
                passwordTokenRepository.delete(t);
            }
        });
    }
}
