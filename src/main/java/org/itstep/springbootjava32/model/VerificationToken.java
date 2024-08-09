package org.itstep.springbootjava32.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Component
@Entity(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    @UpdateTimestamp
    private LocalDateTime expiryDate;
    private Integer userId;

    public VerificationToken(Integer id, String token, LocalDateTime expiryDate) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
