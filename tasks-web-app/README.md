# Tasks Web App

This web app uses MVC architecture to manage a task list. The features that
form this task web app are described as [features]. The [technical specs] for
this web app are also described here.

## Features

- Add a new task
- Get a list of tasks
- Update a task
- Delete a task

## Technical Specs

- API uses HTML pages with JSP
- Languages is Java
- Spring Boot library is used for the MVC web app
- Spring is used a the dependency injection framework
- Spring rest template is used to make calls to the backend Task Service
- Lombok framework is used to annotate form objects to minimise boilerplate code
- Maven is used a building and packaging framework
- Maven Spring-Boot plugin is used for deployment testing

## How To Build

mvn clean package -DskipTests

## How To Run

mvn spring-boot:run

## Web pages

- Get list of tasks: GET  http://localhost:8090/tasks


## Remaining To Dos

- Integration tests used a running instance of backend Task Service.
  Use Wiremock to stub calls to Task Service in the Controller integration tests
- Separate the call to backend Task Service rest APIs into a service which uses a backend HTTP client
- Use Task DTO marshalled into JSON to send and receive data to and from the backend Task Service
- Add unit tests using Mockitio to mock the HTTP client
- Use Hibernate Validation for the input forms data
- Use Controller Advice as global exception handler to catch unhandled exceptions with redirect to error page
- Error page


[features]: #features
[technical specs]: #technical-specs
