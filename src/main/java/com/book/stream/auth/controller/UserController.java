package com.book.stream.auth.controller;



import com.book.stream.auth.entity.User;
import com.book.stream.auth.service.UserService;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.dto.response.ErrorDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("userProfile/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserProfile(@PathVariable Long userId) {
        try {
            logger.info("Received request to fetch user profile for user ID: {}", userId);

            User user = userService.getUserById(userId);

            logger.info("Successfully fetched user profile for user ID: {}", userId);
            return ResponseEntity.ok(ApiResponse.success(user));

        } catch (Exception e) {
            logger.error("Error fetching user profile for user ID {}: {}", userId, e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails("Fetch User Error", "An error occurred while fetching user profile.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(errorDetails));
        }
    }
}

