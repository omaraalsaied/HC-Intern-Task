package com.example.task.services;

import com.example.task.domains.Author;
import com.example.task.exceptions.GeneralException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> saveOrUpdate(Author author) throws GeneralException;

    Void delete(Long id);
}
