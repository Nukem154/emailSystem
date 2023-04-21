package dev.nukem.emailsystem.controller;

import dev.nukem.emailsystem.dto.user.UserCreateDto;
import dev.nukem.emailsystem.dto.user.UserDto;
import dev.nukem.emailsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDto> findAllUsers(@RequestParam(defaultValue = "") String keyword,
                                      @RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "15") Integer size) {
        return userService.findAllUsersByKeyword(keyword, PageRequest.of(page, size, Sort.by("createdOn").descending()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        userService.createUser(userCreateDto);
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable Long id, @RequestBody @Valid UserCreateDto userCreateDto) {
        userService.editUser(id, userCreateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
