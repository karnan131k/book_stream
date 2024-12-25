package com.book.stream.service.impl;

import com.book.stream.dto.request.IssuedBookRequestDTO;
import com.book.stream.entity.Book;
import com.book.stream.entity.IssuedBook;
import com.book.stream.entity.Student;
import com.book.stream.repo.BookRepository;
import com.book.stream.repo.IssuedBookRepository;
import com.book.stream.repo.StudentRepository;
import com.book.stream.service.IssuedBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedBookServiceImpl implements IssuedBookService {

    private static final Logger logger = LoggerFactory.getLogger(IssuedBookServiceImpl.class);

    @Autowired
    private IssuedBookRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public IssuedBook createIssuedBook(IssuedBookRequestDTO dto) {

        logger.debug("Creating IssuedBook with data: {}", dto);
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        Student student = studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));
        IssuedBook issuedBook = IssuedBook.builder()
                .count(dto.getCount())
                .duration(dto.getDuration())
                .issueDate(dto.getIssueDate())
                .student(student)
                .book(book)
                .build();
        return repository.save(issuedBook);
    }

    @Override
    public IssuedBook updateIssuedBook(Long id, IssuedBookRequestDTO dto) {
        logger.debug("Updating IssuedBook with ID: {}", id);
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        Student student = studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));

        IssuedBook issuedBook = repository.findById(id).orElseThrow(() -> new RuntimeException("IssuedBook not found"));
        issuedBook.setCount(dto.getCount());
        issuedBook.setDuration(dto.getDuration());
        issuedBook.setIssueDate(dto.getIssueDate());
        issuedBook.setBook(book);
        issuedBook.setStudent(student);
        return repository.save(issuedBook);
    }

    @Override
    public void deleteIssuedBook(Long id) {
        logger.debug("Deleting IssuedBook with ID: {}", id);
        IssuedBook issuedBook = repository.findById(id).orElseThrow(() -> new RuntimeException("IssuedBook not found"));
        repository.delete(issuedBook);
    }

    @Override
    public List<IssuedBook> getAllIssuedBooks() {
        logger.debug("Fetching all IssuedBooks");
        return repository.findAll();
    }

    @Override
    public IssuedBook getIssuedBookById(Long id) {
        logger.debug("Fetching IssuedBook with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("IssuedBook not found"));
    }


}
