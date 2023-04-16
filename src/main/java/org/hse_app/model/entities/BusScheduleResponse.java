package org.hse_app.model.entities;

import java.util.List;

public class BusScheduleResponse {
    Integer revision;
    List<Bus> buses;

    public BusScheduleResponse(Integer revision, List<Bus> buses) {
        this.revision = revision;
        this.buses = buses;
    }
}
