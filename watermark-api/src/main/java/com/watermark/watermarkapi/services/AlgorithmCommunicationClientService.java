package com.watermark.watermarkapi.services;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Service
public class AlgorithmCommunicationClientService {

    private final RestTemplate restTemplate;

    private static String algorithmUrl = "http://127.0.0.1:5000/algorithm";

    public AlgorithmCommunicationClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation("postForEntity jsonBody")
    @ResponseBody
    public String getWatermarkedImage(String url, String algorithmType) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("url", url);
        map.add("algorithm", algorithmType);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(algorithmUrl, request, String.class );

        return responseEntity.getBody();
    }

}
