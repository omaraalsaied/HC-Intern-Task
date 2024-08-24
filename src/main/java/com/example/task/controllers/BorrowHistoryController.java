package com.example.task.controllers;

import com.example.task.domains.BorrowHistory;
import com.example.task.services.BorrowHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/history")
@Log4j2
public class BorrowHistoryController {

    @Autowired
    private BorrowHistoryService borrowHistoryService;

    @GetMapping
    public ResponseEntity<?> findAll()
    {
        return new ResponseEntity<>(borrowHistoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        Optional<BorrowHistory> record = borrowHistoryService.findById(id);
        if (record.isEmpty())
            return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BorrowHistory record)
    {
        return new ResponseEntity<>(borrowHistoryService.saveOrUpdate(record), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BorrowHistory record)
    {
       borrowHistoryService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("there's now record with the Id " + id + "was found")
        );

        log.info(record);
        record.setId(id);
        return new ResponseEntity<>(borrowHistoryService.saveOrUpdate(record), HttpStatus.ACCEPTED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        borrowHistoryService.delete(id);
        return ResponseEntity.ofNullable("record Deleted Successfully");
    }

    @GetMapping("/borrower/{borrowerId}")
    public ResponseEntity<?> getBorrowHistoriesByAuthorId(@PathVariable Long borrowerId)
    {
        return new ResponseEntity<>(borrowHistoryService.findBorrowHistoryByBorrowerId(borrowerId), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getBorrowHistoriesByBookId(@PathVariable Long bookId)
    {
        return new ResponseEntity<>(borrowHistoryService.findBorrowHistoryByBookId(bookId), HttpStatus.OK);
    }
}
