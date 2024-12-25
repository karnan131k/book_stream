package com.book.stream.service;

import com.book.stream.dto.request.BookRequestDTO;
import com.book.stream.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookRequestDTO dto);
    Book updateBook(Long id, BookRequestDTO dto);
    void deleteBook(Long id);
    List<Book> getAllBooks();
    Book getBookById(Long id);
}
