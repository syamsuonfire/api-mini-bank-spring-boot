package com.minibank.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.minibank.dto.ResponseData;
import com.minibank.exception.MethodArgumentTypeMismatchException;
import com.minibank.exception.ResourceNotFoundException;
import com.minibank.model.Person;
import com.minibank.repository.PersonRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // Get All Persons
    @GetMapping
    public ResponseEntity<ResponseData<List<Person>>> getAll() {
        ResponseData<List<Person>> responseData = new ResponseData<>(
            HttpStatus.OK.value(),
            Arrays.asList("Successfully get all persons"),
            personRepository.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/hobby/{hobby}")
    public Iterable<Person> getByHobby(@Valid @PathVariable("hobby") String hobby) {
    return personRepository.findByHobbyIgnoreCaseContaining(hobby);
    }


    // Get Person By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Person>> getById(@Valid @PathVariable("id") Long id) {
        return personRepository.findById(id).map(person -> {
            ResponseData<Person> responseData = new ResponseData<>(
                    HttpStatus.OK.value(),
                    Arrays.asList("Successfully get person by id " + id),
                    person
            );
            return ResponseEntity.ok(responseData);
        }).orElseThrow(() -> {
            throw new ResourceNotFoundException("Person not found with id " + id);
        });
    }


    // Create Person
    @PostMapping
    public ResponseEntity<ResponseData<Person>> create(@Valid @RequestBody Person person, Errors errors) {
        ResponseData<Person> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new MethodArgumentTypeMismatchException(responseData.getMessages().toString().replace("[", "").replace("]", ""));
        }
        responseData.setStatusCode(HttpStatus.OK.value());
        responseData.getMessages().add("Successfully create person");
        responseData.setPayload(personRepository.save(person));
        return ResponseEntity.ok(responseData);
    }

    // Update Person
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Person>> update(@Valid @PathVariable("id") Long id,
            @Valid @RequestBody Person person, Errors errors) {

        ResponseData<Person> responseData = new ResponseData<>();

        if (id != person.getId()) {
            errors.rejectValue("id", "id.notmatch", "id not match");
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new ResourceNotFoundException(responseData.getMessages().toString().replace("[", "").replace("]", ""));
        }
        Optional<Person> temp = personRepository.findById(id);
        if (!temp.isPresent()) {
            errors.reject("person.not.found", "Person not found");
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new ResourceNotFoundException(responseData.getMessages().toString().replace("[", "").replace("]", ""));
        }

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            throw new MethodArgumentTypeMismatchException(responseData.getMessages().toString().replace("[", "").replace("]", ""));
        }

        // Replace account, if not null
        if (person.getAccount() == null) {
            person.setAccount(temp.get().getAccount());
        }
        person.setCreatedAt(temp.get().getCreatedAt());

        responseData.setStatusCode(200);
        responseData.getMessages().add("Successfully update person");
        responseData.setPayload(personRepository.save(person));
        return ResponseEntity.ok(responseData);
    }

    // Delete Person By Id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeOne(@Valid @PathVariable("id") Long id) {

        return personRepository.findById(id).map(person -> {
            personRepository.delete(person);
            ResponseData<Void> responseData = new ResponseData<>(
                    HttpStatus.OK.value(),
                    Arrays.asList("Successfully remove person by id " + id));
            return ResponseEntity.ok(responseData);
        }).orElseThrow(() -> {
            throw new ResourceNotFoundException("Person not found with id " + id);
        });
    }
}