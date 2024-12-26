package com.book.stream.controller;

import com.book.stream.dto.request.StudentRequestDTO;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.entity.Student;
import com.book.stream.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> create(@RequestBody StudentRequestDTO dto) {
        logger.info("Request to create Student");
        return ResponseEntity.ok(ApiResponse.success(service.createStudent(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable Long id, @RequestBody StudentRequestDTO dto) {
        logger.info("Request to update Student with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.updateStudent(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        logger.info("Request to delete Student with ID: {}", id);
        service.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAll() {
        logger.info("Request to fetch all Students");
        return ResponseEntity.ok(ApiResponse.success(service.getAllStudents()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Long id) {
        logger.info("Request to fetch Student with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.getStudentById(id)));
    }

    @GetMapping("/generateNextStudentId")
    public ResponseEntity<ApiResponse<String>> generateNextStudentId() {
        String id = service.generateNextStudentId();
        return ResponseEntity.ok(ApiResponse.success(id));
    }

}

