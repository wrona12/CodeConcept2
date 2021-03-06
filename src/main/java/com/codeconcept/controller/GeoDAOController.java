package com.codeconcept.controller;

import com.codeconcept.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class GeoDAOController {
    @Autowired
    GeoService geoService;

    public GeoDAOController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/recent")
    public String getJSON() throws Exception{

        return geoService.getJSON("http://resources.codeconcept.pl/api/distance/");
    }
}
