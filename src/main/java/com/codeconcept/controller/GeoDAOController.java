package com.codeconcept.controller;

import com.codeconcept.dto.DistanceDTO;
import com.codeconcept.service.GeoService;
import com.codeconcept.service.TimeResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/", produces = "application/json")
@CrossOrigin(origins = "*")
public class GeoDAOController {
    @Autowired
    GeoService geoService;

    public GeoDAOController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/time/walk/{source1}/{destination1}")
    public ResponseEntity<TimeResponse> getWalkTime(@PathVariable String source1, @PathVariable String destination1) throws Exception {
        if(geoService.getWalkTime(source1, destination1)==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(geoService.getWalkTime(source1, destination1), HttpStatus.OK);
    }
}
