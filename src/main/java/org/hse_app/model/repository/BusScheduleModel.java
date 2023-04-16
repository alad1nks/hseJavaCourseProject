package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.model.entities.Bus;

import java.util.ArrayList;

public interface BusScheduleModel {
    void getBuses(ArrayList<String> params);
    Observable<Integer> getRevision();
    void refreshSchedule();
    Observable<ArrayList<Bus>> getBusesResponse();
}
