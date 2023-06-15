package pl.coderslab.charity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken {

    public PasswordResetToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private LocalDateTime expiryDate;

    @PrePersist
    public void setExpiration() {
        LocalDateTime now = LocalDateTime.now();
        expiryDate = now.plusMinutes(EXPIRATION);
    }
}
