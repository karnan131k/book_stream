package com.book.stream.controller;

import com.book.stream.dto.request.AuthorRequestDTO;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.entity.Author;
import com.book.stream.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Author>> create(@RequestBody AuthorRequestDTO dto) {
        logger.info("Request to create author");
        return ResponseEntity.ok(ApiResponse.success(service.createAuthor(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> update(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        logger.info("Request to update author with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.updateAuthor(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        logger.info("Request to delete author with ID: {}", id);
        service.deleteAuthor(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Author>>> getAll() {
        logger.info("Request to fetch all authors");
        return ResponseEntity.ok(ApiResponse.success(service.getAllAuthors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getById(@PathVariable Long id) {
        logger.info("Request to fetch author with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.getAuthorById(id)));
    }

}

