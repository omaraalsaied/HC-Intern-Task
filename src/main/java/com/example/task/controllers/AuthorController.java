package com.example.task.controllers;

import com.example.task.domains.Author;
import com.example.task.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll()
    {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (author.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Optional<Author>> save(@RequestBody Author author)
    {
       Optional<Author> savedAuthor =  authorService.saveOrUpdate(author);
       return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Author authorDetails) {
        Optional<Author> author = authorService.findById(id);
        if (author.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        authorDetails.setId(author.get().getId());
        Optional<Author> savedAuthor = authorService.saveOrUpdate(authorDetails);
        return ResponseEntity.ok(savedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);

        if (authorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }

        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
