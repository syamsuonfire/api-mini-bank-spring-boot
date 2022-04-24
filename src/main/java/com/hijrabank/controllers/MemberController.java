package com.hijrabank.controllers;

import javax.validation.Valid;

import com.hijrabank.dto.ResponseData;
import com.hijrabank.models.entities.Member;
import com.hijrabank.services.MemberService;

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
@RequestMapping("/api/members")
public class MemberController {


    @Autowired
    private MemberService memberService;

    // Get All Members
    @GetMapping
	public ResponseEntity<Iterable<Member>> findAll() {
		Iterable<Member> memberList = memberService.findAll();
		return ResponseEntity.ok(memberList);
	}
	

    // Get Member by Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> findOne(@PathVariable("id") Long id) {
        ResponseData<Member> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(memberService.findOne(id));
        return ResponseEntity.ok(responseData);
    }

    // Create New Member
    @PostMapping
    public ResponseEntity<ResponseData<Member>> create(@Valid @RequestBody Member member, Errors errors) {
        ResponseData<Member> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage()); 
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } 
        responseData.setStatus(true);
        responseData.setPayload(memberService.save(member));
        return ResponseEntity.ok(responseData);
    }

    // Update Member
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> update(@Valid @RequestBody Member member, Errors errors) {
        ResponseData<Member> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage()); 
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } 
        responseData.setStatus(true);
        responseData.setPayload(memberService.save(member));
        return ResponseEntity.ok(responseData);
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> removeOne(@PathVariable("id") Long id) {

        memberService.removeOne(id);
        ResponseData<Member> responseData = new ResponseData<>();
        responseData.getMessages().add("Member deleted successfully");
        responseData.setStatus(true);
        responseData.setPayload(null);
        return ResponseEntity.ok(responseData);
    }

}
