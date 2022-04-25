package ru.kirillov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirillov.entity.User;
import ru.kirillov.response.UserResponse;
import ru.kirillov.service.UserService;

@RestController
@RequiredArgsConstructor
public class TestController {

    private UserService userService;

    @PostMapping
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
}
