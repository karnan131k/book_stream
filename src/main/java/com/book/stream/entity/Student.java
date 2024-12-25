package com.book.stream.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String studentId; // Unique identifier for a student
    private String imagePath;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<IssuedBook> issuedBooks = new ArrayList<>();
}
