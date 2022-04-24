package com.hijrabank.controllers;

import javax.validation.Valid;

import com.hijrabank.dto.ResponseData;
import com.hijrabank.models.entities.Transaction;
import com.hijrabank.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    // Get All Transactions
    @GetMapping
	public ResponseEntity<Iterable<Transaction>> findAll() {
		Iterable<Transaction> transactionList = transactionService.findAll();
		return ResponseEntity.ok(transactionList);
	}
	

    // Get Transaction by Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Transaction>> findOne(@PathVariable("id") Long id) {
        ResponseData<Transaction> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(transactionService.findOne(id));
        return ResponseEntity.ok(responseData);
    }

    // Create New Transaction
    @PostMapping
    public ResponseEntity<ResponseData<Transaction>> create(@Valid @RequestBody Transaction transaction, Errors errors) {
        ResponseData<Transaction> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage()); 
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } 
        responseData.setStatus(true);
        responseData.setPayload(transactionService.save(transaction));
        return ResponseEntity.ok(responseData);
    }

    // Update Member
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Transaction>> update(@Valid @RequestBody Transaction transaction, Errors errors) {
        ResponseData<Transaction> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage()); 
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } 
        responseData.setStatus(true);
        responseData.setPayload(transactionService.save(transaction));
        return ResponseEntity.ok(responseData);
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Transaction>> removeOne(@PathVariable("id") Long id) {

        transactionService.removeOne(id);
        ResponseData<Transaction> responseData = new ResponseData<>();
        responseData.getMessages().add("Transaction deleted successfully");
        responseData.setStatus(true);
        responseData.setPayload(null);
        return ResponseEntity.ok(responseData);
    }

}
