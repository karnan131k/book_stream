package com.book.stream.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Email
    private String email;
    private String mobile;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<IssuedBook> issuedBooks = new ArrayList<>();
}
