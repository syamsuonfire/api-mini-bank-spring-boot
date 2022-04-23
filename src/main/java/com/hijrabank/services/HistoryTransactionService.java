
package com.hijrabank.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hijrabank.models.entities.HistoryTransaction;
import com.hijrabank.models.repos.HistoryTransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HistoryTransactionService {

    @Autowired
    private HistoryTransactionRepo historyTransactionRepo;

    public HistoryTransaction save(HistoryTransaction historyTransaction) {
        return historyTransactionRepo.save(historyTransaction);
    }

    public HistoryTransaction findOne(Long id) {
        Optional<HistoryTransaction> historyTransaction = historyTransactionRepo.findById(id);
        if(!historyTransaction.isPresent()) {
            return null;
        }
        return historyTransaction.get();
    }

    public List<HistoryTransaction> findAll() {
        return historyTransactionRepo.findAll();
    }

    public void removeOne(Long id) {
        historyTransactionRepo.deleteById(id);
    }
    
}
