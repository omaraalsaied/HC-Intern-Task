package com.example.task.controllers;

import com.example.task.domains.Book;
import com.example.task.services.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/books")
@Log4j2
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll()
    {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if(book.isEmpty())
        {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book)
    {
        log.info("request incoming on /api/books, Method: POST, Input is {}", book);
        Book savedBook = bookService.saveOrUpdate(book);
        log.info("record created successfully with the Id {}", savedBook.getId());
        return ResponseEntity.ok(savedBook);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookService.findById(id);
        log.info("Request Incoming on /api/books/{}, Method: PUT Input is {}", id, bookDetails );
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        bookDetails.setId(id);
        Book updatedBook = bookService.saveOrUpdate(bookDetails);
        log.info("book with id {} has been updated successfully", id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Request Incoming on /api/books/{}, Method: Delete", id );
        bookService.delete(id);
        log.info("Book with the id {} has been deleted successfully", id);
        return ResponseEntity.ofNullable("Book Deleted Successfully");
    }

    @GetMapping("/author/{author_id}")
    public ResponseEntity<?> findAuthorBooks(@PathVariable Long author_id)
    {
        log.info("Request Incoming on /api/books/author/{}, Method: Get", author_id );
        return ResponseEntity.ok(bookService.findBooksByAuthor(author_id));

    }
}
