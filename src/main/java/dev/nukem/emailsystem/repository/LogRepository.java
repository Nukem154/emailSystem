package dev.nukem.emailsystem.repository;

import dev.nukem.emailsystem.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
