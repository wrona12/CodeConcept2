package com.codeconcept.controller;

import com.codeconcept.dto.DistanceDTO;
import com.codeconcept.service.GeoService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getJSON(@PathVariable String source1, @PathVariable String destination1) throws Exception {
        List<DistanceDTO> allDistances = new ArrayList<>();
        String rawString = geoService.getJSON("http://resources.codeconcept.pl/api/distance/");
        JSONObject root = new JSONObject(rawString);
        JSONArray distances = root.getJSONArray("distances");
        for (int i = 0; i < distances.length(); i++) {
            JSONObject jsonDistance = distances.getJSONObject(i);
            DistanceDTO distance = new DistanceDTO();

            String source = jsonDistance.getString("source");
            String destination = jsonDistance.getString("destination");
            int distance1 = jsonDistance.getInt("distance");
            distance.setSource(source);
            distance.setDestination(destination);
            distance.setDistance(distance1);
            allDistances.add(distance);
        }
        allDistances.forEach(System.out::println);
        return geoService.getJSON("http://resources.codeconcept.pl/api/distance/");
//        return source +" "+ destination;
    }
}
