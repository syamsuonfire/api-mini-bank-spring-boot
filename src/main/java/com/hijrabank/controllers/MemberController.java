package com.hijrabank.controllers;

import java.util.List;

import com.hijrabank.models.entities.Member;
import com.hijrabank.rest.BaseResponse;
import com.hijrabank.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    public BaseResponse<List<Member>> findAll() {

        BaseResponse<List<Member>> response = new BaseResponse<List<Member>>();
        response.setStatus(200);
        response.setMessage("Successfully get all members");
        response.setResult(memberService.findAll());
        // response.setResult("memberService.findAll()");

        return response;
    }

    // Get Member by Id
    @GetMapping("/{id}")
    public BaseResponse<List<Member>> findOne(@PathVariable("id") Long id) {

        BaseResponse<List<Member>> response = new BaseResponse<List<Member>>();
        response.setStatus(200);
        response.setMessage("Successfully get member");
        response.setResult(List.of(memberService.findOne(id)));

        return response;
    }

    // Create New Member
    @PostMapping
    public BaseResponse<Member> create(@RequestBody Member member, BindingResult bindingResult) {
        BaseResponse<Member> response = new BaseResponse<Member>();

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Error");
        } else {
            Member newMember = new Member();
            newMember.setName(member.getName());
            newMember.setBirthDate(member.getBirthDate());
            newMember.setAddress(member.getAddress());

            memberService.save(newMember);

            response.setStatus(200);
            response.setMessage("Successfully created");
            response.setResult(newMember);
        }

        return response;
    }

    // Update Member
    @PutMapping
    public BaseResponse<Member> update(@RequestBody Member member, BindingResult bindingResult) {
        BaseResponse<Member> response = new BaseResponse<Member>();

        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("Error");
        } else {
            Member oldMember = memberService.findOne(member.getId());
            oldMember.setName(member.getName());
            oldMember.setBirthDate(member.getBirthDate());
            oldMember.setAddress(member.getAddress());

            memberService.save(oldMember);

            response.setStatus(200);
            response.setMessage("Successfully updated");
            response.setResult(oldMember);
        }

        return response;
    }

    // Delete Member
    @DeleteMapping("/{id}")
    public BaseResponse<Member> removeOne(@PathVariable("id") Long id) {
        BaseResponse<Member> response = new BaseResponse<Member>();

        memberService.removeOne(id);
        response.setStatus(200);
        response.setMessage("Successfully deleted");
        return response;
    }

}
