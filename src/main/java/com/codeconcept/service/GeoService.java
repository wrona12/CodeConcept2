package com.codeconcept.service;

import com.codeconcept.dao.GeoDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class GeoService implements IGeoService {
    GeoDAO geoDAO;

    public GeoService(GeoDAO geoDAO) {
        this.geoDAO = geoDAO;
    }

    public String getJSON(String adres) throws Exception{
        return geoDAO.request(adres);
    }


    @Override
    public String testDAO(String searchFilter) throws Exception{
    geoDAO.request("http://resources.codeconcept.pl/api/distance/");

        return "Bartek";
    }


}
