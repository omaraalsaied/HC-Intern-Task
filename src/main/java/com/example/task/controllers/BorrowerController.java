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
    private BorrowerService borrowerService;

    @GetMapping
    public List<Borrower> findAll()

    {
        return borrowerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Borrower>> getBorrowerById(@PathVariable Long id) {

        return ResponseEntity.ok(borrowerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Optional<Borrower>> save(@RequestBody Borrower borrower)
    {
         return new ResponseEntity<>(borrowerService.saveOrUpdate(borrower), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Borrower borrowerDetails) {
        Optional<Borrower> borrowerOptional = borrowerService.findById(id);

        if (borrowerOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrower not found");

        borrowerDetails.setId(id);
        Optional<Borrower> updatedAuthor = borrowerService.saveOrUpdate(borrowerDetails);
        return new ResponseEntity<>(borrowerService.saveOrUpdate(borrowerDetails), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        borrowerService.delete(id);
        return new ResponseEntity<>("Borrower Deleted Successfully",HttpStatus.NO_CONTENT);
    }
}
