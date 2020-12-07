package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.services.AlgorithmCommunicationClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmCommunicationClientController {

    //main path
    public static final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private final AlgorithmCommunicationClientService algorithmCommunicationClientService;

    public AlgorithmCommunicationClientController(AlgorithmCommunicationClientService algorithmCommunicationClientService) {
        this.algorithmCommunicationClientService = algorithmCommunicationClientService;
    }


    @PostMapping("/image")
    public ResponseEntity<Object> postImageToPython(@RequestParam("algorithm") String algorithm,
                                                    @RequestParam("url") String url, UriComponentsBuilder builder) throws URISyntaxException {

        String response = algorithmCommunicationClientService.getWatermarkedImage(url,algorithm);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Object> a =new ResponseEntity<Object>(response, headers, HttpStatus.CREATED);

        System.out.println("IN CONTROLLER: " + a.toString());

        return new ResponseEntity<Object>(response, headers, HttpStatus.CREATED);
    }

}
