package com.book.stream.auth.service;


import com.book.stream.auth.entity.User;

public interface UserService {
    User getUserById(Long userId);

    User getUserByEmail(String email);
}
