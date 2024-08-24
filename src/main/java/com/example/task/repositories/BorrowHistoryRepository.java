package com.example.task.repositories;

import com.example.task.domains.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
    List<BorrowHistory> findBorrowHistoriesByBookId(Long bookId);
    List<BorrowHistory> findBorrowHistoriesByBorrowerId(Long BorrowerId);
}
