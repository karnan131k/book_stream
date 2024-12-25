package com.book.stream.controller;

import com.book.stream.dto.request.CategoryRequestDTO;
import com.book.stream.dto.response.ApiResponse;
import com.book.stream.entity.Category;
import com.book.stream.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody CategoryRequestDTO dto) {
        logger.info("Request to create Category");
        return ResponseEntity.ok(ApiResponse.success(service.createCategory(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable Long id, @RequestBody CategoryRequestDTO dto) {
        logger.info("Request to update Category with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.updateCategory(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        logger.info("Request to delete Category with ID: {}", id);
        service.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAll() {
        logger.info("Request to fetch all Categories");
        return ResponseEntity.ok(ApiResponse.success(service.getAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getById(@PathVariable Long id) {
        logger.info("Request to fetch Category with ID: {}", id);
        return ResponseEntity.ok(ApiResponse.success(service.getCategoryById(id)));
    }

}

