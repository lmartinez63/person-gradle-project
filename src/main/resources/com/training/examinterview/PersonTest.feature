Feature: Person Testing Scenarios
  Background: A Person
    Given a valid person
  Scenario: A user is able to add a new Person
    Given A object person
    When the person is valid
    Then  the person is added