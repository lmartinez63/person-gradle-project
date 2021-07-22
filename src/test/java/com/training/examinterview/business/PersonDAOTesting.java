package com.training.examinterview.business;

import com.training.examinterview.ExamInterviewApplication;
import com.training.examinterview.entity.Person;
import com.training.examinterview.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ExamInterviewApplication.class)
public class PersonDAOTesting {

    @Autowired
    PersonService personService;

    @Test
    public void save(){
        Person personfake = new Person("99999999","fakeuser",98);
        int beforeUpdate = personService.getAllPersons().size();
        Person personRepository = personService.findPersonByDni(personfake.getDni());
        //person doesn't exists
        assertNull(personRepository);
        //Insert person
        personRepository = personService.save(personfake);
        //When person is inserted is the id not null
        assertNotNull(personRepository.getId());
        List<Person> personListUpdated  = personService.getAllPersons();

        int afterUpdate = personListUpdated.size();
        assertEquals(afterUpdate, (beforeUpdate+1));
        //Delete this person
        personService.delete(personfake);
        afterUpdate = personService.getAllPersons().size();

        assertEquals(afterUpdate, beforeUpdate);

    }
}
