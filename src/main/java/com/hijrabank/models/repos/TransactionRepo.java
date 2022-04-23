package com.hijrabank.models.repos;

import com.hijrabank.models.entities.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    
}
