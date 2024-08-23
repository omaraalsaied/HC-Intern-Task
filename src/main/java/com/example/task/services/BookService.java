package com.example.task.services;

import com.example.task.domains.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book saveOrUpdate (Book book);
    void delete(Long id);
    List<Book> findBooksByAuthor(Long author_id);
}
