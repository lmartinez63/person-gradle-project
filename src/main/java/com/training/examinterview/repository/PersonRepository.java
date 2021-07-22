package com.training.examinterview.repository;

import com.training.examinterview.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findPersonByName(String name);
    List<Person> findPersonByAge(int age);
    Optional<Person> findPersonByDni(String dni);
}
