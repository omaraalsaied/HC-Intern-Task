package com.example.task.services.impl;

import com.example.task.domains.Author;
import com.example.task.domains.Book;
import com.example.task.repositories.BookRepository;
import com.example.task.services.AuthorService;
import com.example.task.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> saveOrUpdate(Book book) {
        Author author = authorService.findById(book.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author with id doesn't exist"));
        book.setAuthor(author);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Void delete(Long id) {
       bookRepository.deleteById(id);
       return null;
    }

    @Override
    public List<Object> findByAuthor(Long author_id) {
        return List.of();
    }
}
