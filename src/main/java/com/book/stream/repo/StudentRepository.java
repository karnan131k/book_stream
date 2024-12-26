package com.book.stream.repo;

import com.book.stream.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom queries can be added here if needed
    Optional<Student> findTopByOrderByStudentIdDesc();
}
