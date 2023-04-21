package dev.nukem.emailsystem.service;

import dev.nukem.emailsystem.entity.User;
import dev.nukem.emailsystem.enums.LogType;

public interface LogService {
    void generateLog(User user, LogType type);
}
