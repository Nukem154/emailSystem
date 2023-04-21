package dev.nukem.emailsystem.dto.user;

import dev.nukem.emailsystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(@NotBlank String username, @NotBlank @Email String email) {
    public static User fromDto(final UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.username)
                .email(userCreateDto.email)
                .build();
    }
}
