package com.book.stream.service;

import com.book.stream.dto.request.AuthorRequestDTO;
import com.book.stream.entity.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(AuthorRequestDTO dto);
    Author updateAuthor(Long id, AuthorRequestDTO dto);
    void deleteAuthor(Long id);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
}
