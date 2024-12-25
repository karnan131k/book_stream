package com.book.stream.auth.service;


import com.book.stream.auth.dto.request.SignupRequest;
import com.book.stream.auth.entity.User;

public interface AuthService {

    User signUp(SignupRequest signupRequest);

    void resetPassword(Long userId, String currentPassword, String newPassword, String confirmPassword);

    void updatePassword(String email, String newPassword);
}
