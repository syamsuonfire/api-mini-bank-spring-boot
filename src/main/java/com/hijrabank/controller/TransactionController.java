package com.hijrabank.controller;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


	@PostMapping("/{accountId}/withdrawal/{amountToDebit}")
	public Transaction debit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToDebit") BigDecimal amountToDebit) throws Exception {
		return transactionService.withdrawal(accountId, amountToDebit);
	}


	@PostMapping("/{accountId}/credit/{amountToCredit}")
	public Transaction credit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToCredit") BigDecimal amountToCredit) throws Exception {
		return transactionService.credit(accountId, amountToCredit);
	}

}
