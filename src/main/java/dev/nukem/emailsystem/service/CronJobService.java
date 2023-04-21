package dev.nukem.emailsystem.service;

import dev.nukem.emailsystem.entity.CronJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CronJobService {

    void createEmailSendingCronJob(String expression);

    void editEmailSendingCronJobExpression(Long id, String expression);

    void deleteCronJob(Long id);

    Page<CronJob> findAllCronJobs(Pageable pageable);
}
