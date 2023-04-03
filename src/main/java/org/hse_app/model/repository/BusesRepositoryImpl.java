package org.hse_app.model.repository;


import org.hse_app.presentation.SchedulePresentation;

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
