package com.hijrabank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hijrabank.models.entities.Member;
import com.hijrabank.models.repos.MemberRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepo memberRepo;

    public Member save(Member member) {
        return memberRepo.save(member);
    }

    public Member findOne(Long id) {
        Optional<Member> member = memberRepo.findById(id);
        if(!member.isPresent()) {
            return null;
        }
        return member.get();
    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }

    public void removeOne(Long id) {
        memberRepo.deleteById(id);
    }
    
}
