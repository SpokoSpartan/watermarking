package com.watermark.watermarkapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

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
