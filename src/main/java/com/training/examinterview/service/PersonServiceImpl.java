package com.training.examinterview.service;

import com.training.examinterview.entity.Person;
import com.training.examinterview.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findPersonsByName(String name) {
        return personRepository.findPersonByName(name);
    }

    @Override
    public List<Person> findPersonsByAge(int age) {
        return personRepository.findPersonByAge(age);
    }

    @Override
    public Person findPersonByDni(String dni) {
        return personRepository.findPersonByDni(dni).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
