package dev.nukem.emailsystem.service;

public interface EmailService {
    void sendEmail(final String to, final String subject, final String text);
}
