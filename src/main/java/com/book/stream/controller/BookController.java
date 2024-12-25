package com.book.stream.controller;

import com.book.stream.dto.request.BookRequestDTO;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.entity.Book;
import com.book.stream.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> create(@RequestBody BookRequestDTO dto) {
        logger.info("Request to create Book");
        return ResponseEntity.ok(ApiResponse.success(service.createBook(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> update(@PathVariable Long id, @RequestBody BookRequestDTO dto) {
        logger.info("Request to update Book with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.updateBook(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        logger.info("Request to delete Book with ID: {}", id);
        service.deleteBook(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAll() {
        logger.info("Request to fetch all Books");
        return ResponseEntity.ok(ApiResponse.success(service.getAllBooks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getById(@PathVariable Long id) {
        logger.info("Request to fetch Book with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.getBookById(id)));
    }

}

