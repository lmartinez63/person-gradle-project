package com.training.examinterview.controller;

import com.training.examinterview.entity.Person;
import com.training.examinterview.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> fetchAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/find_by_name/{name}")
    public ResponseEntity<?> findPersonByName(@PathVariable(value = "name") String name) {
        List<Person> persons = personService.findPersonsByName(name);
        if (!persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find_by_age/{age}")
    public ResponseEntity<?> findPersonByAge(@PathVariable(value = "age") int age) {
        List<Person> persons = personService.findPersonsByAge(age);
        if (!persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find_by/{name}/{age}")
    public ResponseEntity<?> findPersonsByNameAndAge(@PathVariable(value = "name") String name, @PathVariable(value = "age") int age) {
        Predicate<Person> findPersons = (p -> p.getName().equals(name) && p.getAge() == age);
        List<Person> persons = personService.getAllPersons().stream().filter(findPersons).collect(Collectors.toList());
        if (!persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePerson(@Valid @RequestBody Person person) {
        if(person.getDni() == null || person.getDni().isEmpty())
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        Person personRepository = personService.findPersonByDni(person.getDni());
        if (personRepository == null) {
            Person newPerson = personService.save(person);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        } else {
            personRepository.setAge(person.getAge());
            personRepository.setName(person.getName());
            //This is for updates other attributes than DNI
            Person updatedPerson = personService.save(personRepository);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        }
    }
}
