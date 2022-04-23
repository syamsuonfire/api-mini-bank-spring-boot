package com.hijrabank.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hijrabank.models.entities.Transaction;
import com.hijrabank.models.repos.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Transaction findOne(Long id) {
        Optional<Transaction> transaction = transactionRepo.findById(id);
        if(!transaction.isPresent()) {
            return null;
        }
        return transaction.get();
    }

    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    public void removeOne(Long id) {
        transactionRepo.deleteById(id);
    }
    
}
