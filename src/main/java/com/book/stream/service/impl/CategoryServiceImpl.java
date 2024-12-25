package com.book.stream.service.impl;

import com.book.stream.dto.request.CategoryRequestDTO;
import com.book.stream.entity.Category;
import com.book.stream.repo.CategoryRepository;
import com.book.stream.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createCategory(CategoryRequestDTO dto) {
        logger.debug("Creating Category with data: {}", dto);
        Category category = Category.builder()
                .name(dto.getName())
                .imagePath(dto.getImagePath())
                .build();
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryRequestDTO dto) {
        logger.debug("Updating Category with ID: {}", id);
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.getName());
        category.setImagePath(dto.getImagePath());
        return repository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        logger.debug("Deleting Category with ID: {}", id);
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        repository.delete(category);
    }

    @Override
    public List<Category> getAllCategories() {
        logger.debug("Fetching all Categories");
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        logger.debug("Fetching Category with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }


}
