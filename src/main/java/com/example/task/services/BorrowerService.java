package com.example.task.services;

import com.example.task.domains.Borrower;
import com.example.task.repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BorrowerService  {

    List<Borrower> findAll();
    Optional<Borrower> findById(Long id);
    Optional<Borrower> saveOrUpdate(Borrower borrower);
    void delete(Long id);
}
