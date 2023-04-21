package dev.nukem.emailsystem.controller;

import dev.nukem.emailsystem.service.EmailSenderService;
import dev.nukem.emailsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailSenderService emailSenderService;
    private final UserService userService;

    @GetMapping("/{userId}")
    public void sendEmailToUser(@PathVariable Long userId) {
        emailSenderService.sendDefaultEmailToUser_Rest(userService.findUserById(userId));
    }
}
