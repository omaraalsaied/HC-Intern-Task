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
    @Autowired
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
    public Book saveOrUpdate(Book book)  {
        authorService.findById(book.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not Found"));
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
           throw new EntityNotFoundException("book not found");
        }
       bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByAuthor(Long author_id)
    {
        Optional<Author> author = Optional.of(authorService.findById(author_id))
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        log.info(author);
        return bookRepository.findBooksByAuthor(author);
    }
}
