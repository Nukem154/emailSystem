package dev.nukem.emailsystem.service.impl;

import dev.nukem.emailsystem.dto.email.EmailStatisticsDto;
import dev.nukem.emailsystem.repository.UserRepository;
import dev.nukem.emailsystem.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;

    @Override
    public Page<EmailStatisticsDto> getUsersEmailStats(final Pageable pageable) {
        return userRepository.getUserEmailStatistics(pageable).map(EmailStatisticsDto::toDto);
    }
}
