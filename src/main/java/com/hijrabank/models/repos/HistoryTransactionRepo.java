
package com.hijrabank.models.repos;

import com.hijrabank.models.entities.HistoryTransaction;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryTransactionRepo extends JpaRepository<HistoryTransaction, Long> {
    
}
