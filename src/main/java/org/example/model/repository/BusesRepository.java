package org.example.model.repository;


public interface BusesRepository {
    public void getBuses(String day, String direction, String station);
    public void refreshSchedule();
}
