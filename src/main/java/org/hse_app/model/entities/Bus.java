package org.hse_app.model.entities;

public final class Bus {
    public int getDay() {
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

    private final int day;
    private final Long dayTime;
    private final String dayTimeString;
    private final String direction;
    private final int id;
    private final String station;
    public Bus(int id, int day, Long dayTime, String dayTimeString, String direction, String station) {
        this.id = id;
        this.day = day;
        this.dayTime = dayTime;
        this.dayTimeString = dayTimeString;
        this.direction = direction;
        this.station = station;
    }

    @Override
    public String toString() {
        return "\nid:" + String.valueOf(id) + "\nday:" + String.valueOf(day) + "\ndayTime:" + String.valueOf(dayTime) +
                "\ndaytimeString:" + dayTimeString + "\ndirection:" + direction + "\nstation:" + station;
    }
}
