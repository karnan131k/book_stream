package com.book.stream.auth.service.impl;

import com.book.stream.auth.dto.request.SignupRequest;
import com.book.stream.auth.entity.ERole;
import com.book.stream.auth.entity.Role;
import com.book.stream.auth.entity.User;
import com.book.stream.auth.exception.SignUpException;
import com.book.stream.auth.exception.UserNotFoundException;
import com.book.stream.auth.repo.RoleRepository;
import com.book.stream.auth.repo.UserRepository;
import com.book.stream.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User signUp(SignupRequest signupRequest) {

        try {
            // Fetch the ROLE_ADMIN role from the database
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role ROLE_ADMIN is not found."));

            // Build the User entity
            User user = User.builder()
                    .username(signupRequest.getUsername())
                    .email(signupRequest.getEmail())
                    .phoneNumber(signupRequest.getPhoneNumber())
                    .password(new BCryptPasswordEncoder().encode(signupRequest.getPassword()))
                    .roles(new HashSet<>(Collections.singletonList(userRole))) // Add fetched role
                    .build();

            // Save the User
            return userRepository.save(user);
        } catch (Exception e) {
            throw new SignUpException("Exception occurred in signUp method", e);
        }
    }

    @Override
    public void resetPassword(Long userId, String currentPassword, String newPassword, String confirmPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        // Assuming you have a method to encode passwords
        String encodedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);
        userRepository.save(user);
        logger.info("Password updated for user with email: {}", email);
    }
}
