package com.book.stream.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorRequestDTO {

    private String name;

    private String imagePath;
}
