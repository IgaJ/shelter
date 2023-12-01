package com.example.shelter;

import com.example.shelter.entity.Box;
import com.example.shelter.service.BoxService;
import com.example.shelter.service.BoxServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final BoxService boxService;

    @Autowired
    public DataInitializer(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostConstruct
    public void initializeBoxes() {
        for (int i = 0; i <= 5 ; i++) {
            Box box = new Box();
            box.setNumber(i);
            boxService.saveNewBox(true, i);
        }
        for (int j = 6; j <= 10 ; j++) {
            Box box = new Box();
            box.setNumber(j);
            boxService.saveNewBox(false, j);
        }
    }
}
