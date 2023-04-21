package dev.nukem.emailsystem.util;

import dev.nukem.emailsystem.entity.User;

public class EmailGenerator {

    public static String generateTextForDefaultEmail(final User user) {
        return String.format(
                "Ім'я користувача: %s\n" +
                        "Дата та час створення: %s",
                user.getUsername(), user.getCreatedOn());
    }
}
