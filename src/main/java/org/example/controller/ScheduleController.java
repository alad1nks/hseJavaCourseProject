package org.example.controller;

import org.example.model.repository.BusesRepository;
import org.example.model.repository.BusesRepositoryImpl;

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
