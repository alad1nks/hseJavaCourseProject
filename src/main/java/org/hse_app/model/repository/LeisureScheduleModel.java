package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.model.entities.LeisureScheduleResponse;

import java.sql.SQLException;
import java.util.List;

public interface LeisureScheduleModel {
    void getLeisure(List<String> params) throws SQLException;
    void refreshSchedule();
    Observable<LeisureScheduleResponse> getLeisureResponse();
}
