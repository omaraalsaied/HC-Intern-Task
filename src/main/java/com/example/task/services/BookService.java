package com.example.task.services;

import com.example.task.domains.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> saveOrUpdate (Book book);
    Void delete(Long id);
    List<Object> findByAuthor(Long author_id);
}
