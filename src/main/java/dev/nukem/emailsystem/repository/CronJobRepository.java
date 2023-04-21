package dev.nukem.emailsystem.repository;

import dev.nukem.emailsystem.entity.CronJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronJobRepository extends JpaRepository<CronJob, Long> {
}
