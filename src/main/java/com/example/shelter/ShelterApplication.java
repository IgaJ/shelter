package com.example.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterApplication.class, args);
	}

}

// 1 - testy
// 2 - localDateTime (przyjecie, vaccinated), zwracanie wyników różnie posortowanych)
// 3 - jak zrobić żeby sortować po enum (tabela zapisuje miejscie na liście nie wartość string)
// enum adoptionReady Y/N. If Y - select. if N - print info - z enumem czy Stringiem?
// volunteer - łączone tabele: spacery (data, name), sprzatanie (data, box), szkolenie (data)
// jeśli data <7dni, to box do sprzątania (select box to be cleaned), jeśłi spacer <2 dni to pies do wyprowadzenia (select dogs to be walked)