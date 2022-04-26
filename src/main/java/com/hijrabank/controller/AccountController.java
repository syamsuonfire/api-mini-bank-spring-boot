package com.hijrabank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hijrabank.dto.Balance;
import com.hijrabank.dto.ResponseData;
import com.hijrabank.exception.MethodArgumentTypeMismatchException;
import com.hijrabank.exception.ResourceNotFoundException;
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
    public ResponseEntity<ResponseData<Account>> createAccount(@Valid @PathVariable(value = "personId") Long personId, @Valid @RequestBody Account account, Errors errors) {
        ResponseData<Account> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new MethodArgumentTypeMismatchException(responseData.getMessages().toString().replace("[", "").replace("]", ""));
        }
		return personRepository.findById(personId).map(person -> {
			account.setPerson(person);
			responseData.setStatusCode(HttpStatus.OK.value());
			responseData.getMessages().add("Successfully create person");
			responseData.setPayload(accountRepository.save(account));
			return ResponseEntity.ok(responseData);
		
		}).orElseThrow(() -> new ResourceNotFoundException("PersonId " + personId + " not found"));
	}

	@PostMapping("/block/{accountId}")
	public ResponseEntity<ResponseData<Account>> blockAccount(
			@Valid @PathVariable(value = "accountId") Long accountId) {

		ResponseData<Account> responseData = new ResponseData<>();
		return accountRepository.findById(accountId).map(account -> {
			account.setActive(false);
			responseData.setStatusCode(HttpStatus.OK.value());
			responseData.getMessages().add("Successfully block account");
			responseData.setPayload(accountRepository.save(account));
			return ResponseEntity.ok(responseData);
		}).orElseThrow(() -> new ResourceNotFoundException("AccountId " + accountId + " not found"));

	}

	@GetMapping("/balance/{accountId}")
	public ResponseEntity<ResponseData<Balance>> getBalance(@PathVariable(value = "accountId") Long accountId) {

		ResponseData<Balance> responseData = new ResponseData<>();
		return accountRepository.findById(accountId).map(account -> {
			responseData.setStatusCode(HttpStatus.OK.value());
			responseData.getMessages().add("Successfully get account balance");
			responseData.setPayload(new Balance(account.getBalance()));
			return ResponseEntity.ok(responseData);
		}).orElseThrow(() -> new ResourceNotFoundException("AccountId " + accountId + " not found"));

	}

}
