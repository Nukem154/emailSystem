package dev.nukem.emailsystem.service;

import dev.nukem.emailsystem.dto.user.UserCreateDto;
import dev.nukem.emailsystem.dto.user.UserDto;
import dev.nukem.emailsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findUserById(Long id);

    Page<UserDto> findAllUsersByKeyword(String keyword, Pageable pageable);

    void createUser(UserCreateDto userCreateDto);

    void editUser(Long id, UserCreateDto userCreateDto);

    void deleteUser(Long id);
}
