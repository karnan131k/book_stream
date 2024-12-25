package com.book.stream.auth.service.impl;


import com.book.stream.auth.entity.User;
import com.book.stream.auth.exception.UserNotFoundException;
import com.book.stream.auth.repo.UserRepository;
import com.book.stream.auth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + email));
    }
}
