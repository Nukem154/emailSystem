package dev.nukem.emailsystem.service.impl;

import dev.nukem.emailsystem.entity.User;
import dev.nukem.emailsystem.enums.LogType;
import dev.nukem.emailsystem.repository.UserRepository;
import dev.nukem.emailsystem.service.EmailSenderService;
import dev.nukem.emailsystem.service.EmailService;
import dev.nukem.emailsystem.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.nukem.emailsystem.util.EmailGenerator.generateTextForDefaultEmail;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    public static final String DEFAULT_SUBJECT = "Вітання!";

    private final EmailService emailService;
    private final LogService logService;
    private final UserRepository userRepository;

    @Override
    public void sendDefaultEmailToAllUsers_Cron() {
        for (User user : userRepository.findAll()) {
            sendDefaultEmailToUser(user);
            logService.generateLog(user, LogType.CRON);
        }
    }

    @Override
    public void sendDefaultEmailToUser_Rest(final User user) {
        sendDefaultEmailToUser(user);
        logService.generateLog(user, LogType.REST);
    }

    private void sendDefaultEmailToUser(final User user) {
        emailService.sendEmail(user.getEmail(), DEFAULT_SUBJECT, generateTextForDefaultEmail(user));
    }
}
