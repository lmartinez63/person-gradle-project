package com.training.examinterview;

import com.google.gson.Gson;
import com.training.examinterview.entity.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonControllerCucumberSteps {
    private static final Person personfake = new Person("99999999","fakeuser",98);
    private static final String BASE_URL = "http://localhost:8080";

    private static String jsonPersonFake;
    private static Response response;

    @Given("A object person")
    public void aObjectPerson() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        Gson gson = new Gson();
        jsonPersonFake = gson.toJson(personfake);

    }

    @When(" the person is valid")
    public void addPerson() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body(jsonPersonFake)
                .post("/api/persons/save");
    }

    @Then("The person is added")
    public void personIsAdded() {
        assertEquals(201, response.getStatusCode());
    }
}
