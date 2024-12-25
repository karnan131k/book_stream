package com.book.stream.auth.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PasswordUpdateRequest {

    private String email;
    private String newPassword;
    private String confirmPassword;
}
