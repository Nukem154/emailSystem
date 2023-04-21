package dev.nukem.emailsystem.service.impl;

import dev.nukem.emailsystem.dto.user.UserCreateDto;
import dev.nukem.emailsystem.dto.user.UserDto;
import dev.nukem.emailsystem.entity.User;
import dev.nukem.emailsystem.exception.EmailTakenException;
import dev.nukem.emailsystem.exception.UserNotFoundException;
import dev.nukem.emailsystem.exception.UsernameTakenException;
import dev.nukem.emailsystem.repository.UserRepository;
import dev.nukem.emailsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.nukem.emailsystem.dto.user.UserCreateDto.fromDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Page<UserDto> findAllUsersByKeyword(final String keyword, final Pageable pageable) {
        return userRepository.findUsersWhereUsernameOrEmailContains(keyword, pageable).map(UserDto::toDto);
    }

    @Override
    public void createUser(final UserCreateDto userCreateDto) {
        validateUsernameIsAvailable(userCreateDto.username());
        validateEmailIsAvailable(userCreateDto.email());
        userRepository.save(fromDto(userCreateDto));
    }

    private void validateUsernameIsAvailable(final String username) {
        userRepository.findByUsernameIgnoreCase(username).ifPresent(u -> {
            throw new UsernameTakenException();
        });
    }

    private void validateEmailIsAvailable(final String email) {
        userRepository.findByEmailIgnoreCase(email).ifPresent(u -> {
            throw new EmailTakenException();
        });
    }

    @Override
    public void editUser(final Long id, final UserCreateDto userCreateDto) {
        final User user = findUserById(id);
        editUserData(user, userCreateDto);
        userRepository.save(user);
    }

    private void editUserData(final User user, final UserCreateDto userCreateDto) {
        changeUsernameIfNeeded(user, userCreateDto);
        changeEmailIfNeeded(user, userCreateDto);
    }

    private void changeUsernameIfNeeded(final User user, final UserCreateDto userCreateDto) {
        final String oldUsername = user.getUsername();
        final String newUsername = userCreateDto.username();

        if (!oldUsername.equalsIgnoreCase(newUsername)) {
            validateUsernameIsAvailable(newUsername);
            user.setUsername(newUsername);
        }
    }

    private void changeEmailIfNeeded(final User user, final UserCreateDto userCreateDto) {
        final String oldEmail = user.getEmail();
        final String newEmail = userCreateDto.email();

        if (!oldEmail.equalsIgnoreCase(newEmail)) {
            validateEmailIsAvailable(newEmail);
            user.setUsername(newEmail);
        }
    }

    @Override
    public void deleteUser(final Long id) {
        userRepository.delete(findUserById(id));
    }
}
