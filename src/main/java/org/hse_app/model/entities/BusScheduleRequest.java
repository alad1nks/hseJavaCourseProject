package org.hse_app.model.entities;

import java.util.List;

public class BusScheduleRequest {
    private List<BusRequest> busSchedule;

    public List<BusRequest> getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(List<BusRequest> busSchedule) {
        this.busSchedule = busSchedule;
    }
}
