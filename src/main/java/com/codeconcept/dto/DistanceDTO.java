package com.codeconcept.dto;

public class DistanceDTO {
    private String source;
    private String destination;
    private int distance;

    public DistanceDTO() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DistanceDTO{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                '}';
    }
}
