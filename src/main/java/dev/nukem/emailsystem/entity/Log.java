package dev.nukem.emailsystem.entity;

import dev.nukem.emailsystem.enums.LogType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private LogType type;

    @CreationTimestamp
    private LocalDateTime createdOn;

    public Log(User user, LogType type) {
        this.user = user;
        this.type = type;
    }
}
