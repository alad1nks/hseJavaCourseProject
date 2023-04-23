package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.entities.BusScheduleResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BusScheduleModel {
    void getBuses(List<String> params) throws SQLException;
    void refreshSchedule();
    Observable<BusScheduleResponse> getBusesResponse();
}
