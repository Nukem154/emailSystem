package dev.nukem.emailsystem.dto.email;

import java.time.LocalDateTime;

public interface UserEmailStatistics {
    String getUsername();

    String getEmail();

    int getRestCount();

    int getCronCount();

    LocalDateTime getFirst();

    LocalDateTime getLast();
}
