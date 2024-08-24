package com.example.task.services.impl;

import com.example.task.domains.Borrower;
import com.example.task.repositories.BorrowerRepository;
import com.example.task.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Override
    public List<Borrower> findAll() {
        return borrowerRepository.findAll();
    }

    @Override
    public Optional<Borrower> findById(Long id) {
        return borrowerRepository.findById(id);
    }

    @Override
    public Optional<Borrower> saveOrUpdate(Borrower borrower) {
        return Optional.of(borrowerRepository.save(borrower));
    }

    @Override
    public void delete(Long id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if(borrower.isEmpty())
            throw new IllegalArgumentException("No Borrower with this ID was found");
        borrowerRepository.deleteById(id);
    }
}
