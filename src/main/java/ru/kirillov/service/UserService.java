package ru.kirillov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirillov.entity.User;
import ru.kirillov.repository.UserRepository;
import ru.kirillov.response.UserResponse;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Transactional
    public UserResponse getUserById(long id) {
        return userRepository.getUserById(id);
    }
}
