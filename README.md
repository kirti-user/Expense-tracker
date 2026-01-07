Expense Tracker Application
Overview

The Expense Tracker application is a simple CRUD (Create, Read, Update, Delete) API built using Spring Boot. The application allows users to manage expense categories and track individual expenses. Data is persisted using Spring Data JPA, and REST APIs are exposed for managing the entities.

Features

Manage expense categories (e.g., Food, Transportation, etc.).

Track individual expenses (e.g., rent, groceries, etc.).

Supports CRUD operations via REST APIs.

Built using Spring Boot with Maven for build management.

H2 database (or another configured database) for storing expenses and categories.

Exception handling with custom error responses.

Clean, layered architecture with Controllers, Services, Repositories, and DTOs.

Technologies Used

Java 17 (or higher)

Spring Boot 2.x

Spring Data JPA (for ORM and database interaction)

Spring MVC (for REST API)

Maven (for build management)

H2 Database (or any database configured in application.properties)

JUnit (for basic unit tests)

Project Structure
src/
 ├── main/
 │   ├── java/
 │   │   └── com/
 │   │       └── example/
 │   │           └── expensetracker/
 │   │               ├── controllers/
 │   │               │   ├── CategoryController.java
 │   │               │   ├── ExpenseController.java
 │   │               ├── entities/
 │   │               │   ├── Category.java
 │   │               │   ├── Expense.java
 │   │               ├── dtos/
 │   │               │   ├── CategoryDto.java
 │   │               │   ├── ExpenseDto.java
 │   │               ├── services/
 │   │               │   ├── CategoryService.java
 │   │               │   ├── ExpenseService.java
 │   │               │   ├── CategoryServiceImpl.java
 │   │               │   ├── ExpenseServiceImpl.java
 │   │               ├── repositories/
 │   │               │   ├── CategoryRepository.java
 │   │               │   ├── ExpenseRepository.java
 │   │               ├── mappers/
 │   │               │   ├── CategoryMapper.java
 │   │               │   ├── ExpenseMapper.java
 │   │               ├── exceptions/
 │   │               │   ├── GlobalExceptionHandler.java
 │   │               │   ├── ResourceNotFoundException.java
 │   │               └── ExpenseTrackerAppApplication.java
 ├── resources/
 │   ├── application.properties
 │   └── static/
 ├── test/
 │   ├── java/
 │   │   └── com/
 │   │       └── example/
 │   │           └── expensetracker/
 │   │               ├── ExpenseTrackerAppApplicationTests.java

Installation
Prerequisites

Java 17 (or higher)

Maven installed on your machine

Steps to Run the Application

Clone the repository:

git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker


Build the project with Maven:

mvn clean install


Run the application:

mvn spring-boot:run


This will start the Spring Boot application on http://localhost:8080.

Test the REST API using Postman or any API testing tool. Below are the available endpoints:

API Endpoints

Categories:

GET /api/categories - Get all categories.

POST /api/categories - Create a new category.

PUT /api/categories/{id} - Update an existing category.

DELETE /api/categories/{id} - Delete a category.

Expenses:

GET /api/expenses - Get all expenses.

GET /api/expenses/{id} - Get a single expense by ID.

POST /api/expenses - Create a new expense.

PUT /api/expenses/{id} - Update an existing expense.

DELETE /api/expenses/{id} - Delete an expense.

Example Request & Response

POST /api/expenses

Request body:

{
  "categoryId": 1,
  "amount": 50.0,
  "description": "Grocery Shopping"
}


Response body:

{
  "id": 1,
  "categoryId": 1,
  "amount": 50.0,
  "description": "Grocery Shopping"
}

Configuration

You can configure your application settings in src/main/resources/application.properties.

For example:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=*******
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
server.port=8080

Custom Error Handling

The application includes global exception handling through GlobalExceptionHandler.java, where custom error messages are returned in case of issues such as resource not found or bad request.

Running Tests

To run the unit tests, use the following Maven command:

mvn test

Example Test Output:
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.example.expensetracker.ExpenseTrackerAppApplicationTests
...
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.23 sec - in com.example.expensetracker.ExpenseTrackerAppApplicationTests

Contributing

Feel free to fork this project, create pull requests, and contribute any enhancements or bug fixes. Please ensure all contributions are well-documented and include tests where applicable.

Optional Enhancements for the Future:

Add authentication and authorization (JWT-based or OAuth).

Integrate with front-end frameworks (e.g., React, Angular, or Vue.js).

Deploy on Cloud services like AWS or Heroku.

Implement more detailed reports and analytics for expenses.
