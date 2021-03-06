package com.codeconcept.controller;

import com.codeconcept.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/", produces = "application/json")
@CrossOrigin(origins = "*")
public class GeoDAOController {
    @Autowired
    GeoService geoService;

    public GeoDAOController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/time/walk/{source}/{destination}")
    public String getJSON(@PathVariable String source, @PathVariable String destination) throws Exception{

        return geoService.getJSON("http://resources.codeconcept.pl/api/distance/");
//        return source +" "+ destination;
    }
}
