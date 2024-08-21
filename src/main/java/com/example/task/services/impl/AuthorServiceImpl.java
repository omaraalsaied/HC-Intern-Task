package com.example.task.services.impl;

import com.example.task.domains.Author;
import com.example.task.repositories.AuthorRepository;
import com.example.task.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        return authorRepository.save(author);
    }

    public Void delete(Long id)
    {
        authorRepository.deleteById(id);
        return null;
    }

}
