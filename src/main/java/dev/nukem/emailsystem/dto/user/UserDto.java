package dev.nukem.emailsystem.dto.user;

import dev.nukem.emailsystem.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(String username, String email, LocalDateTime createdOn) {
    public static UserDto toDto(final User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .createdOn(user.getCreatedOn())
                .build();
    }
}
