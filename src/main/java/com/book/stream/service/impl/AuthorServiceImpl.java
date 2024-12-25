package com.book.stream.service.impl;

import com.book.stream.dto.request.AuthorRequestDTO;
import com.book.stream.entity.Author;
import com.book.stream.exception.ResourceNotFoundException;
import com.book.stream.repo.AuthorRepository;
import com.book.stream.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository repository;

    @Override
    public Author createAuthor(AuthorRequestDTO dto) {
        logger.debug("Creating author with data: {}", dto);
        Author author = Author.builder()
                .name(dto.getName())
                .imagePath(dto.getImagePath())
                .build();
        return repository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, AuthorRequestDTO dto) {
        logger.debug("Updating author with ID: {}", id);
        Author author = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        author.setName(dto.getName());
        author.setImagePath(dto.getImagePath());
        return repository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        logger.debug("Deleting author with ID: {}", id);
        Author author = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        repository.delete(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        logger.debug("Fetching all authors");
        return repository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        logger.debug("Fetching author with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }


}
