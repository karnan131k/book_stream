package com.book.stream.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentRequestDTO {
    private String name;
    private String studentId;
    private String imagePath;
    private String email;
    private String mobile;
}