# Tasks Service

This service uses a Restful API architecture to manage a task list. The features that form
this task service are described as [features]. The [technical specs] for
this service are also described here.

## Features

- Add a new task
- Get a list of tasks
- Update a task
- Delete a task

## Technical Specs

- API uses JSON media type for the data in request and response exchange
- Languages is Java
- Jersey library is used for the Rest API
- Guice is used a the dependency injection framework
- Database is in-memory Hash Map
- Lombok framework is used to annotate domain objects to minimise boilerplate code
- Maven is used a building and packaging framework
- Maven Jetty plugin is used for deployment testing

## How To Build

mvn clean package

## How To Run

mvn jetty:run

## API Requests

- Get list of tasks: GET http://localhost:8080/tasks-service/tasks
- Add a new task: POST http://localhost:8080/tasks-service/tasks
                  Body: {
                          "id": 1,
                          "description":"Test task",
                          "priority": 1
                        }
- Update an existing task: PUT http://localhost:8080/tasks-service/tasks
                  Body: {
                           "id": 1,
                           "description":"Test task update",
                           "priority": 2
                        }
- Delete an existing task: DELETE http://localhost:8080/tasks-service/tasks/1485085904860
                  Where ${taskId} : 1485085904860

## Remaining To Dos

- TaskDao integration test for in-memory database
- TasksService unit test using Mockitio to mock TaskDao
- Validation of input data
- Usage of TaskDto to separate concerns of client input with the data persistence entity
- Mapper to perform transformation between Task DTO and Task Entity domain object
- Global exception handler to map exception into appropriate HTTP response code
- Error API specification


[features]: #features
[technical specs]: #technical-specs
