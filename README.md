# Animal shelter manager application
This project serves as a trainig implementation for an animal shelter, providing solutions for animal care.  
The application ofers RESTful endpoints for managing animals their living spaces, and their health.

This is a training project designed to showcase fundamental concepts. If you find the functionality intriguing, your contributions and ideas are highly encouraged. This project has the potential for expansion based on interest, making it an opportunity to develop or to support institutions working with animals.

## Tehnologies used
<li>Java
<li>Spring Boot
<li>Spring Data JPA
<li>Spring MVC
<li>Lombok
<li>H2 Database (in-memory)
<li>MySQl

## Project structure
<li>controllers: Contains REST controllers for animals and boxes (rooms)
<li>dto: Data Transfer Objects for communication between controllers and services
<li>entities: JPA entities for animals and boxes
<li>repositories: Spring Data JPA repositories for interacting with the database
<li>services: Business logic services to manage animals
<li>mappers: Converters between DTOs and entities
<li>initializer: Data initializer to create initial rooms during application startup

## Endpoints
Animals
<li>POST /api/v1/animals: Create a new animal
<li>GET /api/v1/search: return a list of animals filered by specified parameters (name, age, sex, size, etc.)
<li>POST /api/v1/animals/{id}/transfer: move to room without quarantoine status
<li>POST /api/v1/animals/{id}/changeBox: change to room with specified params
<li>DELETE /api/v1/animals/{id}: delete an animal by ID
<li>PATCH /api/v1/animals: update an existing animal data

Boxes (rooms)
<li>GET /api/v1/boxes: retrieve a list of all boxes (rooms)
<li>GET /api/v1/boxes/{id}: retrieve a box by ID
<li>GET /api/v1/boxes/available: retrieve a list of rooms with available space
<li>DELETE /api/v1/boxes/id/{id}: delete a box by ID

## Security (work in progress)
The application is a work in progress and basic security features will be implemented soon. Stay tuned for updates and feel free to contribute!





