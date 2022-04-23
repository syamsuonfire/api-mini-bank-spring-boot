package com.hijrabank.controllers;

import java.util.List;


import com.hijrabank.models.entities.Member;
import com.hijrabank.models.entities.Transaction;
import com.hijrabank.rest.BaseResponse;
import com.hijrabank.services.MemberService;
import com.hijrabank.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MemberService memberService;


    // Get All Transactions
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/transactions")
    @ResponseBody
    public BaseResponse<List<Transaction>> findAll() {

        BaseResponse<List<Transaction>> response = new BaseResponse<List<Transaction>>();
        response.setStatus(200);
        response.setMessage("Successfully get all transaction");
        response.setResult(transactionService.findAll());

        return response;
    }

    // Get Transaction by Id
    @GetMapping("/{id}")
    public BaseResponse<List<Transaction>> findOne(@PathVariable("id") Long id) {

        BaseResponse<List<Transaction>> response = new BaseResponse<List<Transaction>>();
        response.setStatus(200);
        response.setMessage("Successfully get transaction");
        response.setResult(List.of(transactionService.findOne(id)));

        return response;
    }

    // Create New Transaction
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/transactions")
    @ResponseBody
    public BaseResponse<Transaction> create(@RequestBody Transaction transaction, BindingResult bindingResult, @RequestParam(value = "member_id") long idMember) {
        BaseResponse<Transaction> response = new BaseResponse<Transaction>();

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Error");
        } else {
            Transaction newTransaction = new Transaction();
            newTransaction.setAmount(transaction.getAmount()); 
            newTransaction.setStatus(transaction.getStatus());
            newTransaction.setTransactionDate(transaction.getTransactionDate());

            Member userMember = memberService.findOne(idMember);
            newTransaction.setMember(userMember);


            transactionService.save(newTransaction);

            response.setStatus(200);
            response.setMessage("Successfully created");
            response.setResult(newTransaction);
        }

        return response;
    }

    // Update Transaction
    @PutMapping
    public BaseResponse<Transaction> update(@RequestBody Transaction transaction, BindingResult bindingResult) {
        BaseResponse<Transaction> response = new BaseResponse<Transaction>();

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Error");
        } else {
            Transaction oldTransaction = transactionService.findOne(transaction.getId());
            oldTransaction.setAmount(transaction.getAmount()); 
            oldTransaction.setStatus(transaction.getStatus());
            oldTransaction.setTransactionDate(transaction.getTransactionDate());

            transactionService.save(oldTransaction);

            response.setStatus(200);
            response.setMessage("Successfully updated");
            response.setResult(oldTransaction);
        }

        return response;
    }

    // Delete Transaction
    @DeleteMapping("/{id}")
    public BaseResponse<Transaction> removeOne(@PathVariable("id") Long id) {
        BaseResponse<Transaction> response = new BaseResponse<Transaction>();

        transactionService.removeOne(id);
        response.setStatus(200);
        response.setMessage("Successfully deleted");
        return response;
    }

}
