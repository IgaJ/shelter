package com.example.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterApplication.class, args);
	}

}

// spr implementację patchById
// spr impl detele by id (boolean zamiast void)
// select by date before lub inaczej - query w repo
// spr profilowanie (sql/h2)


// 1 - testy
// 3 - jak zrobić żeby wyświetlać w tabeli wartość String enum zamiast miejsca na liście - custom converter implements AttributeConverter
// enum adoptionReady Y/N. If Y - select. if N - print info - z enumem czy Stringiem?
// łączone tabele: spacery (data, animal name, volunteer name), sprzatanie (data, box, volunteer name), pielegnacja (data, animal name, care description, visit description)
// dodać zachowania:
// jeśli data sprzątania <7dni, to box do sprzątania (set box lastCleanongDate to now), jeśli spacer <2 dni to spacer (set lastWalkDate to now)