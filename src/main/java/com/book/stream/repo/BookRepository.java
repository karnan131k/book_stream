package com.book.stream.repo;

import com.book.stream.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom queries can be added here if needed
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findBooksByTitleContaining(@Param("title") String title);
}