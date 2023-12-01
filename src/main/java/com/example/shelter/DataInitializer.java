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
        for (int i = 0; i <= 4 ; i++) {
            boxService.saveNewBox(true);
        }
        for (int j = 5; j <= 9 ; j++) {
            boxService.saveNewBox(false);
        }
    }
}
