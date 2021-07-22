package com.training.examinterview.controller;

import com.google.gson.Gson;
import com.training.examinterview.entity.Person;
import com.training.examinterview.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void fetchAllPersons_basicTest() throws Exception {

        //call GET "/api/persons" application-json "fetchAllPersons"
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/persons").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

    }

    @Test
    public void findPersonByNameXXXXXXX() throws Exception {
        String name = "XXXXXXXX";
        //call GET "/api/persons/find_by_name/{name}" application-json "find_by_name"
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/persons/find_by_name/"+name).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void savePerson() throws Exception {
        Person personfake = new Person("99999999","fakeuser",98);
        Gson gson = new Gson();
        String json = gson.toJson(personfake);
        //call POST "/api/persons/save" application-json "savePerson"
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/persons/save").contentType(MediaType.APPLICATION_JSON).content(json);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void savePersonWithInvalidData() throws Exception {
        List<Person> badRequestPersons = new ArrayList<Person>();
        badRequestPersons.add(new Person("99999999","fakeuser",9));
        badRequestPersons.add(new Person("99999999","fa",98));
        badRequestPersons.add(new Person("99999999","fakeuser",100));
        Gson gson = new Gson();
        badRequestPersons.stream().forEach(
                personfake -> {
                    String json = gson.toJson(personfake);
                    //call POST "/api/persons/save" application-json "savePerson"
                    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/persons/save").contentType(MediaType.APPLICATION_JSON).content(json);
                    try {
                        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    @Test
    public void findByNameXAndAge99() throws Exception {
        String name = "X";
        int age = 99;
        //call GET "/api/persons/find_by_name/{name}" application-json "find_by_name"
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/persons/find_by/"+name+"/"+age).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
    }

}
