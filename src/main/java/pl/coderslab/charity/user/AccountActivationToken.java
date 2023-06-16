package pl.coderslab.charity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_activation_tokens")
@Getter
@Setter
@NoArgsConstructor
public class AccountActivationToken {

    public AccountActivationToken(User user, String token) {
        this.user = user;
        this.token = token;
    }

    private static final int EXPIRATION = 60 * 24 * 30 * 3;
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
        this.expiryDate = now.plusMinutes(EXPIRATION);
    }
}
