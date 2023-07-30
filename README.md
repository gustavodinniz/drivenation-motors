# DriveNation Motors - Car Dealership Management System

The DriveNation Motors project is a management system for a fictional car dealership. It consists of three microservices: Vehicle Management, Customer Management, Deal Management.

## Microservices

**Vehicle Management:** This microservice is responsible for managing the vehicle registration at the dealership and the maintenance history.

**Customer Management:** This microservice is responsible for managing the customer registration at the dealership and the interaction history of each customer.

**Deal Management:** This microservice is responsible for transactions that can be of the following types: sales, purchases and vehicle maintenance.

Each microservice is an independent Quarkus application that communicates with the other microservices via HTTP/REST.

## Technologies

- Java 17
- Quarkus
- RESTEasy JAX-RS
- MongoDB with Panache
- Apache Kafka
- Apache Commons CSV
- Swagger UI
- JUnit 5
- Mockito
- RestAssured

## How to run

Each microservice is a separate Maven project and can be run independently.

### Prerequisites

- Java 17
- Maven
- MongoDB

### Execution

Navigate to the directory of the microservice you want to run.

Run the following Maven command to start the application:

The application will start and will be available at the port defined in the application.properties file.

./mvnw compile quarkus:dev

## Tests

Each microservice has unit and integration tests that can be run with Maven using the command:

./mvnw test

## API Documentation

The API documentation for each microservice is available via Swagger UI at the following URL:

http://localhost:<port>/swagger-ui/

## Author

Gustavo Diniz

## License

This project is under the MIT license. See the LICENSE file for more details.
