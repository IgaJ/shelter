package com.example.shelter.controller;

import com.example.shelter.model.AnimalDTO;
import com.example.shelter.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDTO> saveNewAnimal (@RequestBody AnimalDTO animalDTO) {
        AnimalDTO savedAnimal = animalService.saveNewAnimal(animalDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/animals/id/" + savedAnimal.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AnimalDTO> listAnimals() {
        return animalService.listAnimals();
    }

    // skłądnia
    //localhost:8080/animals?name=Rudy
    @GetMapping(params = "name") // param = tylko wtedy metoda uruchomiona gdy dostarczony jest ten parametr
    public List<AnimalDTO> getAnimalByName(/*@RequestParam*/ String name) {
        return animalService.getAnimalByName(name);
    }

    @GetMapping(params = "age")
    public List<AnimalDTO> getAnimalByAge(@RequestParam Integer age) {
        return animalService.getByAge(age);
    }
    @GetMapping(params = "vaccinated")
    public List<AnimalDTO> getVaccinated(@RequestParam String vaccinated) {
        return animalService.getVaccinated(vaccinated);
    }

    @GetMapping("/{id}") // nawias do PathVariable // id zaraz po ukośniku
    public AnimalDTO getAnimalById(@PathVariable ("id") UUID id) {
        return animalService.getAnimalById(id).orElseThrow(null);
    }

    //animals?name=fafik

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") UUID id) {
        animalService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

/*

/api/v1/animals  -> GET/POST
/api/v1/animals?maxAge=5&minAge=2 - request params
/api/v1/animals/id -> GET/PUT/PATCH/DELETE  (tym sposobem robimy update i działemy na jednym)
/api/v1/owners/id/animals/id

//wysylanie przez parametry zapytania url: animals?parametr1=wartosc&parametr2=wartosc&....  -> @RequestParam lub bez adnotacji
//wysylanie przez czesc sciezki najczesciej do id - @PathVariable
//wysylanie przez cialo zapytania, najczesciej obiekty w formacie json - @RequestBody -> referancja do obiektu DTO
//wysylanie danych w naglowkach zapytania (header) -> dane logowania, login i hasło

* */


