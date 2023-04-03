package org.example.model.repository;

import org.example.presentation.SchedulePresentation;

public class BusesRepositoryImpl implements BusesRepository {
    SchedulePresentation schedulePresentation;
    public BusesRepositoryImpl() {
        this.schedulePresentation = new SchedulePresentation();
    }
    @Override
    public void getBuses(String day, String direction, String station) {

    }

    @Override
    public void refreshSchedule() {

    }
}
