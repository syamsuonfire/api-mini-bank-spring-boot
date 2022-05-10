package com.minibank.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.minibank.exception.KamuKorupsiException;
import com.minibank.model.Account;
import com.minibank.model.Person;
import com.minibank.model.Transaction;
import com.minibank.repository.AccountRepository;
import com.minibank.repository.PersonRepository;
import com.minibank.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Transactional
	public Transaction debit(Long accountId, BigDecimal amountToDebit) throws Exception {

		BigDecimal totalDayDebit = transactionRepository.totalDayDebit(accountId) == null
				? BigDecimal.valueOf(0).movePointLeft(2)
				: transactionRepository.totalDayDebit(accountId);

		Account account = accountRepository.findById(accountId).get();
		
		if (totalDayDebit.add(amountToDebit).compareTo(account.getDailyDebitLimit()) >0 ) {
			throw new Exception("Daily Limit exceeded!");
		}
			
			
		if (!account.isActive()) {
			throw new Exception("Account inactive!");
		}
		
		if (account.getBalance().compareTo(amountToDebit) >= 0) {
			account.setBalance(account.getBalance().subtract(amountToDebit));
		} else {
			throw new Exception("Balance not satisfatory!");
		}

		accountRepository.save(account);

		Transaction transaction = new Transaction(account, amountToDebit, 2);

		return transactionRepository.save(transaction);

	}

	@Transactional
	public Transaction credit(Long accountId, BigDecimal amountToCredit) throws Exception {

		Account account = accountRepository.findById(accountId).get();

		if (!account.isActive()) {
			throw new Exception("Account inactive!");
		}

		// Jika credit lebih besar dari balance
		Optional<Person> temp = personRepository.findById(account.getPerson().getId());
		double tempDouble =amountToCredit.doubleValue();
		if ( tempDouble > temp.get().getSalary()) {
			throw new KamuKorupsiException("Kamu Korupsi");
		}
		account.setBalance(account.getBalance().add(amountToCredit));

		accountRepository.save(account);

		Transaction transaction = new Transaction(account, amountToCredit, 1);

		return transactionRepository.save(transaction);

	}
}
