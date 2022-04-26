package com.hijrabank.repository;

import java.util.List;

import com.hijrabank.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByHobbyIgnoreCaseContaining(String hobby);
}
