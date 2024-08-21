package com.example.task.services;

import com.example.task.domains.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> getById(Long id);
    Author create(Author author);
    Author update(Author author);
    Void delete(Long id);
}
