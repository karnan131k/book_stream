package com.book.stream.service;

import com.book.stream.dto.request.IssuedBookRequestDTO;
import com.book.stream.entity.IssuedBook;

import java.util.List;

public interface IssuedBookService {
    IssuedBook createIssuedBook(IssuedBookRequestDTO dto);
    IssuedBook updateIssuedBook(Long id, IssuedBookRequestDTO dto);
    void deleteIssuedBook(Long id);
    List<IssuedBook> getAllIssuedBooks();
    IssuedBook getIssuedBookById(Long id);
}
