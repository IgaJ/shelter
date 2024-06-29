package com.example.shelter;

import com.example.shelter.box.BoxService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
            boxService.addNewBox(true);
        }
        for (int i = 5; i <= 9 ; i++) {
            boxService.addNewBox(false);
        }
    }
}
