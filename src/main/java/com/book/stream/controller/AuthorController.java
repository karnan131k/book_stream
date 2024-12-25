package com.book.stream.controller;

import com.book.stream.dto.request.AuthorRequestDTO;
import com.book.stream.entity.Author;
import com.book.stream.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorRequestDTO dto) {
        logger.info("Request to create author");
        return ResponseEntity.ok(service.createAuthor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        logger.info("Request to update author with ID: {}", id);
        return ResponseEntity.ok(service.updateAuthor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Request to delete author with ID: {}", id);
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        logger.info("Request to fetch all authors");
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id) {
        logger.info("Request to fetch author with ID: {}", id);
        return ResponseEntity.ok(service.getAuthorById(id));
    }

}

