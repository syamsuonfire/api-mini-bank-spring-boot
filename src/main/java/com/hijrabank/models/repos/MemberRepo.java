package com.hijrabank.models.repos;

import com.hijrabank.models.entities.Member;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepo extends JpaRepository<Member, Long> {
    
}
