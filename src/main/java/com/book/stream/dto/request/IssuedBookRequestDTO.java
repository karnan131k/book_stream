package com.book.stream.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IssuedBookRequestDTO {
    private int count;
    private int duration;
    private LocalDate issueDate;
    private Long bookId;
    private Long studentId;
}
