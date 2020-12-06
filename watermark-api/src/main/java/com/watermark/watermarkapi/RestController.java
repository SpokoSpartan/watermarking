package com.watermark.watermarkapi;

import com.watermark.watermarkapi.services.AlgorithmCommunicationClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @Autowired
    AlgorithmCommunicationClientService algorithmCommunicationClientService;

    @RequestMapping(value={"/algorithm"}, method= RequestMethod.POST,  produces = {"application/json"})
    public @ResponseBody
    String getAttr(@RequestParam String url, @RequestParam String algorithmType)
    {
        return "Url: " + url + "Algorithm: " + algorithmType;
    }

    @RequestMapping(value={"/algorithm"}, method= RequestMethod.GET,  produces = {"application/json"})
    public @ResponseBody
    String getWatermark(@RequestParam String url, @RequestParam String algorithmType)
    {
        return "Url: " + url + "Algorithm: " + algorithmType;
    }

}
