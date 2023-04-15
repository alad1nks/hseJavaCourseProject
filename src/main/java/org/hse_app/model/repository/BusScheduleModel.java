package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.DataBase;

public interface BusScheduleModel {
    public void getBuses(String day, String direction, String station);

    public Observable<Integer> getRevision();
    public void refreshSchedule();
}
