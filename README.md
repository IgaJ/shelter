# Animal Shelter Manager Application

This project serves as a training implementation for an animal shelter, providing solutions for animal care.  
The application offers RESTful endpoints for managing animals, their living spaces, and their health.

This is a training project designed to showcase fundamental concepts. If you find the functionality intriguing, your contributions and ideas are highly encouraged.

## Interaction with the System
Operations such as vaccination, health checking, walking, adoption, and box cleaning can be triggered through appropriate HTTP requests. This enables users of the application (e.g., shelter staff) to interact with the system and update the status of animals and boxes.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- Lombok
- H2 Database (in-memory)
- MySQL

## Project Structure
- **controllers**: Contains REST controllers for animals, boxes (rooms), and tasks performed (e.g., walking, feeding, health checking, box cleaning)
- **dto**: Data Transfer Objects for communication between controllers and services
- **entities**: JPA entities for animals, boxes, and tasks for both
- **repositories**: Spring Data JPA repositories for interacting with the database
- **services**: Business logic services
- **mappers**: Converters between DTOs and entities
- **initializer**: Data initializer to create initial rooms during application startup

## Endpoints

### Animals
- **POST /api/v1/animals**: Create a new animal
- **GET /api/v1/animals/search**: Return a list of animals filtered by specified parameters (name, age, sex, size, etc.)
- **POST /api/v1/animals/{id}/transfer**: Move to room without quarantine status
- **POST /api/v1/animals/{id}/changeBox**: Change to room with specified parameters
- **DELETE /api/v1/animals/{id}**: Delete an animal by ID
- **PATCH /api/v1/animals**: Update an existing animal data
- **POST /api/v1/animals/{id}/feed** (or **/walk**, **/health**, **/vaccinate**, **/adopt**): Create a respective task

### Boxes (rooms)
- **GET /api/v1/boxes**: Retrieve a list of all boxes (rooms)
- **GET /api/v1/boxes/{id}**: Retrieve a box by ID
- **GET /api/v1/boxes/available**: Retrieve a list of rooms with available space
- **DELETE /api/v1/boxes/id/{id}**: Delete a box by ID
- **POST /api/v1/boxes/{id}/clean**: Create a respective task

### Tasks
- **GET /api/v1/animals/{id}/tasks**: Retrieve tasks for a specific animal
- **GET /api/v1/boxes/{id}/tasks**: Retrieve tasks for a specific box

## Security (work in progress)
The application is a work in progress and basic security features will be implemented soon.
