package com.codeconcept.controller;

import com.codeconcept.dto.DistanceDTO;
import com.codeconcept.service.GeoService;
import com.codeconcept.service.TimeResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TimeResponse walkTime(@PathVariable String source1, @PathVariable String destination1) throws Exception {
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
        for (DistanceDTO distance : allDistances) {
            if (distance.getSource().equals(source1) && distance.getDestination().equals(destination1)) {
                TimeResponse timeResponse = new TimeResponse();
                int travelTime;
                int v = 6;
                travelTime = distance.getDistance() / v;
                int travelTimeMinutes = travelTime * 60;
                timeResponse.setTime(travelTimeMinutes);
                return timeResponse;
            }
        }
        return null;
    }
}
