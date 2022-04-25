package com.hijrabank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hijrabank.dto.ResponseData;
import com.hijrabank.exception.ResourceNotFoundException;
import com.hijrabank.model.Person;
import com.hijrabank.repository.PersonRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<ResponseData<List<Person>>> getAll() {
        ResponseData<List<Person>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("Successfully get all persons");
        responseData.setPayload(personRepository.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Person>> getById(@Valid @PathVariable("id") Long id) {
        ResponseData<Person> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("Successfully get data person");
        responseData.setPayload(personRepository.findById(id).get());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Person>> create(@Valid @RequestBody Person person, Errors errors) {
        ResponseData<Person> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new RuntimeException(responseData.getMessages().toString()
                .replace(",", ", ")  
                .replace("[", "")  
                .replace("]", "")  
                .trim());
        }
        responseData.setStatus(true);
        responseData.getMessages().add("Successfully create person");
        responseData.setPayload(personRepository.save(person));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Person>> update(@Valid @PathVariable("id") Long id,
            @Valid @RequestBody Person person, Errors errors) {

        if (id != person.getId()) {
            errors.rejectValue("id", "id.notmatch", "id not match");
            ;
        }
        Optional<Person> temp = personRepository.findById(id);
        if (!temp.isPresent()) {
            errors.reject("person.not.found", "Person not found");
        }

        ResponseData<Person> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new RuntimeException(responseData.getMessages().toString()
                .replace(",", ", ")  
                .replace("[", "")  
                .replace("]", "")  
                .trim());
        }
        responseData.setStatus(true);
        responseData.getMessages().add("Successfully update person");
        responseData.setPayload(personRepository.save(person));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Null>> removeOne(@Valid @PathVariable("id") Long id) {

        return personRepository.findById(id).map(person -> {
            personRepository.delete(person);
            ResponseData<Null> responseData = new ResponseData<>();
            responseData.getMessages().add("Member deleted successfully");
            responseData.setStatus(true);
            responseData.setPayload(null);
            return ResponseEntity.ok(responseData);
        }).orElseThrow(() -> {
            throw new ResourceNotFoundException("Member not found with id " + id);
        });
    }
}