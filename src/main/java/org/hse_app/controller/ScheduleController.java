package org.hse_app.controller;


import org.hse_app.model.repository.BusesRepository;
import org.hse_app.model.repository.BusesRepositoryImpl;

public class ScheduleController {
    String day;
    String direction;
    String station;
    BusesRepository busesRepository;
    public ScheduleController() {
        this.busesRepository = new BusesRepositoryImpl();
    }

    private void requestSchedule(String day, String direction, String station) {
        this.busesRepository.getBuses(day, direction, station);
    }
    private void refreshSchedule() {
        this.busesRepository.refreshSchedule();
    }
}
