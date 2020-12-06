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


    String algorithmUrl = "http://127.0.0.1:5000/algorithm";

    @ApiOperation("postForEntity jsonBody")
    @ResponseBody
    public String getWatermarkedImage(String url, String algorithmType) throws URISyntaxException  {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("url", url);
        map.add("algorithm", algorithmType);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity( algorithmUrl, request, String.class );

        //System.out.println("Status Code: " + responseEntity.getStatusCode());
        //System.out.println("Body: " + responseEntity.getBody().toString());
        //System.out.println("Location: " + responseEntity.getHeaders().getLocation());

        return responseEntity.toString();

    }

}
