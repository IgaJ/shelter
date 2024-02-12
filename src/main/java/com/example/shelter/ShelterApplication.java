package com.example.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterApplication.class, args);
	}
}

// przenieść metody pomocnicze działające z boxRepository z animalService do box service
// przenieść metody update z kontrolerów animal/box do akcji(?)
// dodać archiwum zwierząt (adopcja) zamiast usuwania