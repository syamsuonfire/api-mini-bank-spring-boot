package com.hijrabank.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hijrabank.jpa.exception.*;
import com.hijrabank.model.Account;
import com.hijrabank.repository.AccountRepository;
import com.hijrabank.repository.PersonRepository;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PersonRepository personRepository;

	@PostMapping("/create/{personId}")
	public Account createAccount(@PathVariable(value = "personId") Long personId, @Valid @RequestBody Account account) {
		return personRepository.findById(personId).map(person -> {
			account.setPerson(person);
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("PersonId " + personId + " not found"));
	}

	@PostMapping("/block/{accountId}")
	public Account blockAccount(@PathVariable(value = "accountId") Long accountId) {
		return accountRepository.findById(accountId).map(account -> {
			account.setActive(false);
			;
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("Account Id " + accountId + " not found"));
	}

	@GetMapping("/balance/{accountId}")
	public BigDecimal getBalance(@PathVariable(value = "accountId") Long accountId) {
		return accountRepository.findById(accountId).get().getBalance();
	}
}
