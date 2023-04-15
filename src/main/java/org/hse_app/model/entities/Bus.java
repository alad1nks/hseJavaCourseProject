package org.hse_app.model.entities;

public final class Bus {
    public String getDay() {
        return day;
    }

    public Long getDayTime() {
        return dayTime;
    }

    public String getDayTimeString() {
        return dayTimeString;
    }

    public String getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }

    public String getStation() {
        return station;
    }

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
