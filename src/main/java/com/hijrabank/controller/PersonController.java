package com.hijrabank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hijrabank.model.Person;
import com.hijrabank.repository.PersonRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/persons")
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
    @PostMapping
    public Person create(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@Valid @PathVariable("id") Long id) {
        return personRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public Person update(@Valid @PathVariable("id") Long id, @Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@Valid @PathVariable("id") Long id) {
        personRepository.deleteById(id);
    }
}
