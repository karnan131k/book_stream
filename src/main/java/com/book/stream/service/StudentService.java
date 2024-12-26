package com.book.stream.service;

import com.book.stream.dto.request.StudentRequestDTO;
import com.book.stream.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentRequestDTO dto);
    Student updateStudent(Long id, StudentRequestDTO dto);
    void deleteStudent(Long id);
    List<Student> getAllStudents();
    Student getStudentById(Long id);

    String generateNextStudentId();
}
