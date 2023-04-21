package dev.nukem.emailsystem.controller;

import dev.nukem.emailsystem.dto.email.EmailStatisticsDto;
import dev.nukem.emailsystem.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public Page<EmailStatisticsDto> getEmailStatistic(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "15") Integer size) {
        return statisticsService.getUsersEmailStats(PageRequest.of(page, size));
    }
}
