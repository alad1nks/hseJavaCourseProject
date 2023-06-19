package org.hse_app.model.repository;

import io.reactivex.rxjava3.core.Observable;
import org.hse_app.model.entities.LeisureScheduleResponse;

import java.sql.SQLException;
import java.util.List;

public class LeisureScheduleModelImpl implements LeisureScheduleModel {
    @Override
    public void getLeisure(List<String> params) throws SQLException {

    }

    @Override
    public void refreshSchedule() {

    }

    @Override
    public Observable<LeisureScheduleResponse> getLeisureResponse() {
        return null;
    }
}
