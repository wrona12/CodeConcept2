package com.codeconcept.service;

import com.codeconcept.dao.GeoDAO;
import com.codeconcept.dto.DistanceDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoService implements IGeoService {
    GeoDAO geoDAO;

    public TimeResponse getWalkTime(String source1, String destination1) throws Exception {
        List<DistanceDTO> allDistances = new ArrayList<>();

        String rawString = geoDAO.request("http://resources.codeconcept.pl/api/distance/");
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
            if (distance.getSource().equals(source1) && distance.getDestination().equals(destination1)||distance.getDestination().equals(source1)&&distance.getSource().equals(destination1)) {

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

    public GeoService(GeoDAO geoDAO) {
        this.geoDAO = geoDAO;
    }
}
