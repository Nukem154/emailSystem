package dev.nukem.emailsystem.service;

import dev.nukem.emailsystem.entity.User;

public interface EmailSenderService {
    void sendDefaultEmailToAllUsers_Cron();
    void sendDefaultEmailToUser_Rest(User user);
}
