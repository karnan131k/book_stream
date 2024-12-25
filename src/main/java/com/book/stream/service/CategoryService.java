package com.book.stream.service;

import com.book.stream.dto.request.CategoryRequestDTO;
import com.book.stream.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequestDTO dto);
    Category updateCategory(Long id, CategoryRequestDTO dto);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
