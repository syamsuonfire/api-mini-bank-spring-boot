package com.hijrabank.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hijrabank.dto.HistoryFilter;
import com.hijrabank.model.Transaction;
import com.hijrabank.repository.TransactionRepository;
import com.hijrabank.service.TransactionService;

@RequestMapping("/api/transactions")
@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	TransactionRepository transactionRepository;

	@GetMapping("/{accountId}")
	public List<Transaction> getAllTransaction(@PathVariable(value = "accountId") Long accountId) throws Exception {
		return transactionRepository.findTransactionByAccountId(accountId);
	}

	@GetMapping("/{accountId}/history")
	public List<Transaction> history(@PathVariable(value = "accountId") Long accountId, @Valid @RequestBody HistoryFilter historyFilter) {
		return transactionRepository.findByAccountIdWithCreationDateBetween(accountId,
				historyFilter.getFromDate(), historyFilter.getToDate());
	}

	@PostMapping("/{accountId}/debit/{amountToDebit}")
	public Transaction debit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToDebit") BigDecimal amountToDebit) throws Exception {
		return transactionService.debit(accountId, amountToDebit);
	}

	@PostMapping("/{accountId}/credit/{amountToCredit}")
	public Transaction credit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToCredit") BigDecimal amountToCredit) throws Exception {
		return transactionService.credit(accountId, amountToCredit);
	}
}
