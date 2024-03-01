# Animal shelter manager application
This project serves as a trainig implementation for an animal shelter, providing solutions for animal care.  
The application ofers RESTful endpoints for managing animals, their living spaces, and archiving various actions performed with animals such as walking, vaccinating, adopting or changing rooms.

This is a training project designed to showcase fundamental concepts. If you find the functionality intriguing, your contributions and ideas are highly encouraged. This project has the potential for expansion based on interest, making it an opportunity to develope the project or to support institutions working with animals.

## Tehnologies used
<li>Java
<li>Spring Boot
<li>Spring Data JPA
<li>Spring MVC
<li>H2 Database (in-memory)
<li>Lombok

## Project structure
<li>controllers: Contains REST controllers for animals, boxes (rooms) and actions
<li>dto: Data Transfer Objects for communication between controllers and services
<li>entities: JPA entities for animals, boxes and actions (operations archive)
<li>repositories: Spring Data JPA repositories for interacting with the database
<li>services: Business logic services to manage animals, boxes and actions
<li>mappers: Converters between DTOs and entities
<li>initializer: Data initializer to create initial rooms during application startup

## Endpoints
Animals
<li>POST /api/v1/animals: Create a new animal
<li>GET /api/v1/animals: Retrieve a list of all animals or filter by parameters (name, age, sex, size, etc.)
<li>GET /api/v1/animals/{id}: Retrieve an animal by ID
<li>DELETE /api/v1/animals/{id}: Delete an animal by ID
<li>PATCH /api/v1/animals: Update an existing animal data

Boxes (rooms)
<li>GET /api/v1/boxes: Retrieve a list of all boxes (rooms)
<li>GET /api/v1/boxes/{id}: Retrieve a box by ID
<li>GET /api/v1/boxes/check/{boxNumber}: Check if a box with a given number is available (f.e. before moving animal in)
<li>GET /api/v1/boxes/available: Retrieve a list of rooms with available space
<li>DELETE /api/v1/boxes/id/{id}: Delete a box by ID
<li>DELETE /api/v1/boxes/number/{number}: Delete a box by number

Actions
<li>POST /api/v1/animals/actions: Save a new action as an archive of operation performed with an animal (f.e. like walk, vaccinate, adopt, room change)
<li>POST /api/v1/boxes/actions: Save a new action for a box (f.e. clean)
<li>GET /api/v1/animals/{id}/actions: Retrieve a list of actions for an animal
<li>GET /api/v1/actions: Retrieve a list of all actions

## Security (work in progress)
The application is a work in progress and basic security features will be implemented soon. Stay tuned for updates and feel free to contribute!

## Usage
1. Clone the repository: https://github.com/IgaJ/shelter.git
2. Navigate to the project directory: cd shelter
3. Build and run the application: mvnw spring-boot:run
The application will be accessible at http://localhost:8080

## 
![pexels-photo-15794988](https://github.com/IgaJ/shelter/assets/110561199/26133d2c-15f0-4e97-b043-ef8b256f9e88)

Photo credit: Bianca Beltrán, pexels.com






