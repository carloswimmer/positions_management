# Positions Management

> **Note:** This project is currently under construction. Some sections of this documentation will be updated as new features are implemented.

A Java study project built with the Spring Boot framework for learning and exploring backend development concepts.

## About

This project is a REST API for managing job positions and candidates. It serves as a hands-on learning experience for Java development using modern Spring Boot practices.

## Technologies

- **Java 17**
- **Spring Boot 3.5.10**
- **Spring Data JPA** - Data persistence and ORM
- **Spring Validation** - Request validation using Jakarta Validation
- **PostgreSQL 16** - Relational database
- **Lombok** - Reduces boilerplate code
- **Maven** - Dependency management and build tool
- **Docker** - Database containerization

## Project Structure

```
src/main/java/com/carloswimmer/positions_management/
├── exceptions/           # Global exception handling
├── modules/
│   └── candidate/        # Candidate module
│       ├── controllers/  # REST controllers
│       └── use_cases/    # Business logic
└── PositionsManagementApplication.java
```

## Prerequisites

- Java 17 or higher
- Maven 3.x
- Docker and Docker Compose

## Getting Started

### 1. Start the database

```bash
docker-compose up -d
```

This will start a PostgreSQL container with the following configuration:
- **Host:** localhost
- **Port:** 5432
- **Database:** positions_management
- **Username:** admin
- **Password:** admin

### 2. Run the application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Candidates

| Method | Endpoint       | Description          |
|--------|----------------|----------------------|
| POST   | /candidates/   | Create a new candidate |

### Create Candidate Example

```bash
curl -X POST http://localhost:8080/candidates/ \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "username": "johndoe",
    "email": "john@example.com",
    "password": "password123",
    "description": "Software Developer",
    "curriculum": "https://linkedin.com/in/johndoe"
  }'
```

## Validation Rules

- **username**: Cannot contain empty spaces
- **email**: Must be a valid email address
- **password**: Must be between 8 and 100 characters

## License

This project is licensed under the MIT License.
