package dev.nukem.emailsystem.dto.email;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmailStatisticsDto(String username, String email, EmailCount count, LocalDateTime first,
                                 LocalDateTime last) {
    public static EmailStatisticsDto toDto(UserEmailStatistics emailStats) {
        return EmailStatisticsDto.builder()
                .username(emailStats.getUsername())
                .email(emailStats.getEmail())
                .count(new EmailCount(emailStats.getRestCount(), emailStats.getCronCount()))
                .first(emailStats.getFirst())
                .last(emailStats.getLast())
                .build();
    }
}

