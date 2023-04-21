package dev.nukem.emailsystem.repository;

import dev.nukem.emailsystem.dto.email.UserEmailStatistics;
import dev.nukem.emailsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    Page<User> findUsersWhereUsernameOrEmailContains(@Param("keyword") String keyword, Pageable pageable);


    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmailIgnoreCase(String email);

    @Query("SELECT u.username as username, u.email as email, "
            + "SUM(CASE WHEN l.type = 'REST' THEN 1 ELSE 0 END) as restCount, "
            + "SUM(CASE WHEN l.type = 'CRON' THEN 1 ELSE 0 END) as cronCount, "
            + "MIN(l.createdOn) as first, MAX(l.createdOn) as last "
            + "FROM User u JOIN Log l ON u.id = l.user.id "
            + "GROUP BY u.id ORDER BY (SUM(CASE WHEN l.type = 'REST' THEN 1 ELSE 0 END) + SUM(CASE WHEN l.type = 'CRON' THEN 1 ELSE 0 END)) DESC")
    Page<UserEmailStatistics> getUserEmailStatistics(Pageable pageable);
}
