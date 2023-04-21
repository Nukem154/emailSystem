package dev.nukem.emailsystem.exception;

public class CronJobNotFoundException extends RuntimeException {
    public CronJobNotFoundException(final Long id) {
        super("Cron job with id " + id + " not found.");
    }
}