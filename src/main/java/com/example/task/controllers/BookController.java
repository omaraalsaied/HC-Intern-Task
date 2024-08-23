package com.example.task.controllers;

import com.example.task.domains.Book;
import com.example.task.repositories.BookRepository;
import com.example.task.services.AuthorService;
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
    private AuthorService authorService;
    private BookRepository bookRepository;

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
    public ResponseEntity<Optional<Book>> save(@RequestBody Book book)
    {
        Optional<Book> savedBook = bookService.saveOrUpdate(book);
        return ResponseEntity.ok(savedBook);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Book bookDetails) {
        //TODO: recoding this this
//        Optional<Book> bookOptional = bookService.findById(id);
//
//        if (bookOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
//        }
//
//        bookDetails.setId(id);
//        Book updatedBook = bookService.saveOrUpdate(bookDetails);
        return ResponseEntity.ok(bookDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);

        if (bookOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @GetMapping("/author")
    // TODO: Create Searching By Author's ID
//    public List<Book> getByAuthor(@RequestParam Long id)
//    {
//        return bookService.getByAuthorId(id);
//    }
}
