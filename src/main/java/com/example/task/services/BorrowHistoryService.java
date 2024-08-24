package com.example.task.services;

import com.example.task.domains.BorrowHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BorrowHistoryService {
    List<BorrowHistory> findAll();
    Optional<BorrowHistory> findById(Long id);
    Optional<BorrowHistory> saveOrUpdate(BorrowHistory borrowHistory);
    void delete(Long id);
    List<BorrowHistory> findBorrowHistoryByBookId(Long bookId);
    List<BorrowHistory> findBorrowHistoryByBorrowerId(Long borrowerId);

}
