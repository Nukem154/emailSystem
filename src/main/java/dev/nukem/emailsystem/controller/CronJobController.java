package dev.nukem.emailsystem.controller;

import dev.nukem.emailsystem.dto.CronJobDto;
import dev.nukem.emailsystem.entity.CronJob;
import dev.nukem.emailsystem.service.CronJobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cron")
@RequiredArgsConstructor
public class CronJobController {

    private final CronJobService cronJobService;

    @GetMapping
    public Page<CronJob> findAllCronJobs(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "15") Integer size) {
        return cronJobService.findAllCronJobs(PageRequest.of(page, size, Sort.by("createdOn").descending()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmailCronJob(@RequestBody @Valid @NotBlank CronJobDto cronJobDto) {
        cronJobService.createEmailSendingCronJob(cronJobDto.expression());
    }

    @PutMapping("/{id}")
    public void editCronJobExpression(@PathVariable Long id, @RequestBody @Valid @NotBlank CronJobDto cronJobDto) {
        cronJobService.editEmailSendingCronJobExpression(id, cronJobDto.expression());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCronJob(@PathVariable Long id) {
        cronJobService.deleteCronJob(id);
    }
}
