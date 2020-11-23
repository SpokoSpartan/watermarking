package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.entities.Picture;
import com.watermark.watermarkapi.repositories.PictureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final PictureRepository repository;

    public HelloController(PictureRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public String findall() {
        String result = "";
        for (Picture pcr : repository.findAll()) {
            result += pcr.toString();
        }

        return result;
    }
}
