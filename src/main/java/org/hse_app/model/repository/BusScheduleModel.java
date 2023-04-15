package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.DataBase;
import org.hse_app.model.entities.Bus;

import java.util.List;

public interface BusScheduleModel {
    void getBuses(String day, String direction, String station);
    Observable<Integer> getRevision();
    void refreshSchedule();
    Observable<List<Bus>> getBusesResponse();
}
