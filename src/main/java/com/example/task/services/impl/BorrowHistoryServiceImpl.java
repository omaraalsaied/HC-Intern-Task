package com.example.task.services.impl;

import com.example.task.domains.BorrowHistory;
import com.example.task.domains.Borrower;
import com.example.task.repositories.BookRepository;
import com.example.task.repositories.BorrowHistoryRepository;
import com.example.task.repositories.BorrowerRepository;
import com.example.task.services.BorrowHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BorrowHistoryServiceImpl implements BorrowHistoryService {

    @Autowired
    private BorrowHistoryRepository borrowHistoryRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BorrowHistory> findAll() {
        return borrowHistoryRepository.findAll();
    }

    @Override
    public Optional<BorrowHistory> findById(Long id) {
        return borrowHistoryRepository.findById(id);
    }

    @Override
    public Optional<BorrowHistory> saveOrUpdate(BorrowHistory borrowHistory) {
        return Optional.of(borrowHistoryRepository.save(borrowHistory));
    }

    @Override
    public void delete(Long id) {
        borrowHistoryRepository.deleteById(id);
    }

    @Override
    public List<BorrowHistory> findBorrowHistoryByBookId(Long bookId)
    {
        bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("Book Not Found")
        );
        return borrowHistoryRepository.findBorrowHistoriesByBookId(bookId);
    }
    @Override
    public List<BorrowHistory> findBorrowHistoryByBorrowerId(Long borrowerId)
    {
        borrowerRepository.findById(borrowerId).orElseThrow(
                () -> new IllegalArgumentException("Borrower Not Found")
        );
        return borrowHistoryRepository.findBorrowHistoriesByBorrowerId(borrowerId);
    }
}
