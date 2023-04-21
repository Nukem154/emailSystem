package dev.nukem.emailsystem.service.impl;

import dev.nukem.emailsystem.entity.CronJob;
import dev.nukem.emailsystem.repository.CronJobRepository;
import dev.nukem.emailsystem.service.CronJobService;
import dev.nukem.emailsystem.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class CronJobServiceImpl implements CronJobService {

    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    private final CronJobRepository cronJobRepository;
    private final TaskScheduler taskScheduler;
    private final EmailSenderService emailSenderService;

    @Override
    public Page<CronJob> findAllCronJobs(final Pageable pageable) {
        return cronJobRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void createEmailSendingCronJob(final String expression) {
        final CronJob cronJob = cronJobRepository.save(new CronJob(expression));
        scheduleCronJob(cronJob.getId(), expression, emailSenderService::sendDefaultEmailToAllUsers_Cron);
    }

    @Override
    @Transactional
    public void editEmailSendingCronJobExpression(final Long id, final String expression) {
        final CronJob cronJob = findCronJobById(id);

        cancelCronJob(id);
        scheduleCronJob(id, expression, emailSenderService::sendDefaultEmailToAllUsers_Cron);

        cronJob.setExpression(expression);
        cronJobRepository.save(cronJob);
    }

    @Override
    @Transactional
    public void deleteCronJob(final Long id) {
        cronJobRepository.delete(findCronJobById(id));
        cancelCronJob(id);
    }

    private CronJob findCronJobById(final Long id) {
        return cronJobRepository.findById(id).orElseThrow();
    }

    private void scheduleCronJob(final Long cronJobId, final String expression, final Runnable task) {
        scheduledTasks.put(cronJobId, taskScheduler.schedule(task, new CronTrigger(expression)));
    }

    private void cancelCronJob(final Long id) {
        scheduledTasks.get(id).cancel(false);
        scheduledTasks.remove(id);
    }

}
