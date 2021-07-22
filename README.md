# person-gradle-project

# Download Repository

# Inside folder run docker compose (here it's the mongodb)
/usr/local/bin/docker-compose -f docker-compose.yaml up -d

# Run Application, application will run in port 8080
./gradlew bootRun

# Goals
1. Create an endpoint to return all the Persons from the db.
   Endpoint: GET http://localhost:8080/api/persons


2. Add an endpoint to accept a Person object and save it to the database.
   Endpoint: POST http://localhost:8080/api/persons/save
   Header: Content-Type: application/json
   Body: {
   "dni": 34568564,
   "name": "Cecilia",
   "age": 26
   }

3. Add Validation to the Person that is received in the previous endpoint:
   a. Name must be at least 3 characters long (not whitespaces).
   Endpoint: POST http://localhost:8080/api/persons/save
   Header: Content-Type: application/json
   Body: { "dni": 34568564, "name": "Ce", "age": 26 }
   ResponseStatusCode Expected: 400

b. Age must be between 18 and 99.
Endpoint: POST http://localhost:8080/api/persons/save
Header: Content-Type: application/json
Body: { "dni": 34568564, "name": "Luis", "age": 2 }
ResponseStatusCode Expected: 400

4. Add an endpoint to retrieve a Person by name.
   Endpoint: GET http://localhost:8080/api/persons/find_by_name/{name}

5. Add an endpoint to retrieve a Person by age.
   Endpoint: GET http://localhost:8080/api/persons/find_by_age/{age}

6. Add an endpoint to retrieve a List of Persons by name and age, but instead of using the repository for getting the results, use Streams to narrow down the results (get all Persons from the db and filter in Java the results).
   Endpoint: GET http://localhost:8080/api/persons/find_by/{name}/{age}

7. When in the previous point there are no results, return a 404 status code.
   Endpoint: GET http://localhost:8080/api/persons/find_by/{name}/{age}
   ResponseStatusCode Expected: 404

8. Write unit tests.
   ./gradlew test
   ./gradlew test --tests com.training.examinterview.business.PersonDAOTesting
9. For controller functionality add test with MockMvc
   ./gradlew test --tests com.training.examinterview.controller.PersonControllerTest

10. Add Cucumber test for the point number 2.
    Not able to add it correctly with Junit5
