package com.book.stream.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookRequestDTO {
    private String title;
    private String imagePath;
    private int stockCount;
    private Long categoryId;
    private Long authorId;
}

