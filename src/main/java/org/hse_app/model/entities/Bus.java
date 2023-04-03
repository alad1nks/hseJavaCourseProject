package org.example.model.entities;

public final class Bus {
    private final String day;
    private final Long dayTime;
    private final String dayTimeString;
    private final String direction;
    private final int id;
    private final String station;
    public Bus(String day, Long dayTime, String dayTimeString, String direction, int id, String station) {
        this.day = day;
        this.dayTime = dayTime;
        this.dayTimeString = dayTimeString;
        this.direction = direction;
        this.id = id;
        this.station = station;
    }
}
