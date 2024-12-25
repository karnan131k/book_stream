package com.book.stream.service.impl;

import com.book.stream.dto.request.StudentRequestDTO;
import com.book.stream.entity.Student;
import com.book.stream.exception.ResourceNotFoundException;
import com.book.stream.repo.StudentRepository;
import com.book.stream.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository repository;

    @Override
    public Student createStudent(StudentRequestDTO dto) {
        logger.debug("Creating Student with data: {}", dto);
        Student student = Student.builder()
                .name(dto.getName())
                .imagePath(dto.getImagePath())
                .studentId(dto.getStudentId())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .build();
        return repository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentRequestDTO dto) {
        logger.debug("Updating Student with ID: {}", id);
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        student.setName(dto.getName());
        student.setImagePath(dto.getImagePath());
        student.setStudentId(dto.getStudentId());
        student.setEmail(dto.getEmail());
        student.setMobile(dto.getMobile());
        return repository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.debug("Deleting Student with ID: {}", id);
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        repository.delete(student);
    }

    @Override
    public List<Student> getAllStudents() {
        logger.debug("Fetching all Students");
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        logger.debug("Fetching Student with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }


}
