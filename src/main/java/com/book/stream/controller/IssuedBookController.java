package com.book.stream.controller;

import com.book.stream.dto.request.IssuedBookRequestDTO;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.entity.IssuedBook;
import com.book.stream.service.IssuedBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issuedBooks")
public class IssuedBookController {

    private static final Logger logger = LoggerFactory.getLogger(IssuedBookController.class);

    @Autowired
    private IssuedBookService service;

    @PostMapping
    public ResponseEntity<ApiResponse<IssuedBook>> create(@RequestBody IssuedBookRequestDTO dto) {
        logger.info("Request to create IssuedBook");
        return ResponseEntity.ok(ApiResponse.success(service.createIssuedBook(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IssuedBook>> update(@PathVariable Long id, @RequestBody IssuedBookRequestDTO dto) {
        logger.info("Request to update IssuedBook with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.updateIssuedBook(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        logger.info("Request to delete IssuedBook with ID: {}", id);
        service.deleteIssuedBook(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IssuedBook>>> getAll() {
        logger.info("Request to fetch all IssuedBooks");
        return ResponseEntity.ok(ApiResponse.success(service.getAllIssuedBooks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IssuedBook>> getById(@PathVariable Long id) {
        logger.info("Request to fetch IssuedBook with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.getIssuedBookById(id)));
    }

}

