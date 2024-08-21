package com.example.task.controllers;

import com.example.task.domains.Author;
import com.example.task.domains.Borrower;
import com.example.task.repositories.BorrowerRepository;
import com.example.task.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public List<Borrower> findAll()

    {
        return borrowerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Borrower>> getBorrowerById(@PathVariable Long id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if (borrower.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(borrower);
    }

    @PostMapping
    public Borrower save(@RequestBody Borrower borrower)
    {
        return borrowerRepository.save(borrower);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Borrower borrowerDetails) {
        Optional<Borrower> borrowerOptional = borrowerRepository.findById(id);

        if (borrowerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }

        borrowerDetails.setId(id);
        Borrower updatedAuthor = borrowerRepository.save(borrowerDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Borrower> borrowerOptional = borrowerRepository.findById(id);

        if (borrowerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }

        borrowerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
