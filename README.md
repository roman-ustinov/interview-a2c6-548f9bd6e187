# Animals Project

## Overview
This project is a Spring Boot application designed to manage animals and their breeds. 
It includes features for creating, updating, and retrieving animals and their related data. 
The project follows a clean architecture with DTOs, services, repositories, and controllers.

## Features
- CRUD operations for animals and breeds.
- Global exception handling.
- Unit and integration tests for services and controllers.
- Database migrations with Liquibase.

## Prerequisites
To run this project, you need the following:
- Java 17
- PostgreSQL 14
- Maven 3.6 or higher
- Git

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/roman-ustinov/interview-a2c6-548f9bd6e187.git
cd interview-a2c6-548f9bd6e187
```

### 2. Configure the Database
1. Create a PostgreSQL database:
   ```bash
   createdb animals_db
   ```

### 3. Configure Profiles
The application supports two profiles:
- **localhost**: Used for local development.
  ```properties
  spring.profiles.active=localhost
  spring.datasource.url=jdbc:postgresql://localhost:5432/animals_db
  spring.datasource.username=local_user
  spring.datasource.password=local_password
  ```
- **prod**: Used for production.
  ```properties
  spring.profiles.active=prod
  spring.datasource.url=jdbc:postgresql://prod-db-server:5432/animals_db
  spring.datasource.username=prod_user
  spring.datasource.password=prod_password
  ```
Profiles can be switched by setting the `spring.profiles.active` property in the `application.properties` file or passing it as a command-line argument:
```bash
-Dspring.profiles.active=prod
```

### 4. Build and Run the Application
1. Build the application:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### 5. Access the Application
- API Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Development Notes

### Folder Structure
- `src/main/java`:
    - `controller`: REST controllers for handling HTTP requests.
    - `service`: Service layer with business logic.
    - `repository`: JPA repositories for database interactions.
    - `model`: JPA entity classes.
    - `dto`: Data Transfer Objects.
    - `mapper`: MapStruct mappers for converting between entities and DTOs.
    - `config`: Configuration classes.
    - `exception`: Custom exceptions and global exception handler.
- `src/main/resources`:
    - `application.properties`: Main configuration file.
    - `application-localhost.properties`: Localhost configuration file.
    - `application-prod.properties`: Production configuration file.
    - `db/`: Liquibase migration files.
- `src/test/java`: Unit and integration tests.

### Database Migrations
Liquibase is used for managing database schema changes. Update `db/liquibase-changelog.xml` for new migrations. Run migrations automatically when the application starts or manually with:
```bash
mvn liquibase:update
```

### Testing
Run tests with:
```bash
mvn test
```

### Key Dependencies
- **Spring Boot**: Application framework.
- **Liquibase**: Database schema management.
- **MapStruct**: Object mapping.
- **JUnit 5**: Testing framework.
- **HikariCP**: Connection pooling.
- **Swagger/OpenAPI**: API documentation.

## Example API Endpoints

### 1. Create A New Animal
**POST** `/api/v1/animals`
```json
{
  "name": "Alfa",
  "age": 3,
  "breedId": 1,
  "gender": "MALE"
}
```

### 2. Get An Animal By ID
**GET** `/api/v1/animals/{id}`

### 3. Get All Animals
**GET** `/api/v1/animals`

### 4. Get Animals With Details
**GET** `/api/v1/animals/details`

### 5. Update An Existing Animal
**PUT** `/api/v1/animals`
```json
{
  "id": 1,
  "name": "Beta",
  "age": 4,
  "breedId": 2,
  "gender": "FEMALE"
}
```

### 6. Delete An Animal
**DELETE** `/api/v1/animals/{id}`

## Contributing
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with detailed explanations.

## License
This project is licensed under the MIT License.