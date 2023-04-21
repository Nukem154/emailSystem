package dev.nukem.emailsystem.service.impl;

import dev.nukem.emailsystem.entity.Log;
import dev.nukem.emailsystem.entity.User;
import dev.nukem.emailsystem.enums.LogType;
import dev.nukem.emailsystem.repository.LogRepository;
import dev.nukem.emailsystem.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public void generateLog(final User user, final LogType type) {
        logRepository.save(new Log(user, type));
    }

}
