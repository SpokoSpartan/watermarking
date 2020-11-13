package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.entities.ImageRepository;
import com.watermark.watermarkapi.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    public HelloController(ImageRepository repository) {
        this.repository = repository;
    }

    private final ImageRepository repository;

    @GetMapping("/test")
    public String findall() {
        String result = "";
        for (Image img : repository.findAll()) {
            result += img.toString();
        }

        return result;
    }
}
