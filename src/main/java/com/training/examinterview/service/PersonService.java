package com.training.examinterview.service;

import com.training.examinterview.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPersons();

    List<Person> findPersonsByName(String name);

    List<Person> findPersonsByAge(int age);

    Person findPersonByDni(String dni);

    Person save(Person person);

    void delete(Person person);

}
