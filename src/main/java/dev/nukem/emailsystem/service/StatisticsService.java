package dev.nukem.emailsystem.service;

import dev.nukem.emailsystem.dto.email.EmailStatisticsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatisticsService {
    Page<EmailStatisticsDto> getUsersEmailStats(Pageable pageable);
}
