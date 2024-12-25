package com.book.stream.service.impl;

import com.book.stream.dto.request.BookRequestDTO;
import com.book.stream.entity.Author;
import com.book.stream.entity.Book;
import com.book.stream.entity.Category;
import com.book.stream.repo.AuthorRepository;
import com.book.stream.repo.BookRepository;
import com.book.stream.repo.CategoryRepository;
import com.book.stream.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book createBook(BookRequestDTO dto) {
        logger.debug("Creating Book with data: {}", dto);

        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = Book.builder()
                .title(dto.getTitle())
                .imagePath(dto.getImagePath())
                .stockCount(dto.getStockCount())
                .author(author)
                .category(category)
                .build();
        return repository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookRequestDTO dto) {
        logger.debug("Updating Book with ID: {}", id);
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        book.setTitle(dto.getTitle());
        book.setImagePath(dto.getImagePath());
        book.setStockCount(dto.getStockCount());
        book.setAuthor(author);
        book.setCategory(category);

        return repository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        logger.debug("Deleting Book with ID: {}", id);
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        repository.delete(book);
    }

    @Override
    public List<Book> getAllBooks() {
        logger.debug("Fetching all Books");
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        logger.debug("Fetching Book with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }


}
