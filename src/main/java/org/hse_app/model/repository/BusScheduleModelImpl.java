package org.hse_app.model.repository;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.hse_app.controller.BusScheduleController;
import org.hse_app.database.DataBase;
import org.hse_app.model.entities.BusScheduleResponse;

import java.sql.SQLException;
import java.util.List;

public class BusScheduleModelImpl implements BusScheduleModel {
    private final DataBase dataBase;
    private final Observable<BusScheduleResponse> requestScheduleToPresentationEmitter;

    public BusScheduleModelImpl() {
        System.out.println("BusScheduleModelImpl");
        dataBase = new DataBase();
        requestScheduleToPresentationEmitter = dataBase.getBusSchedule();
    }

    @Override
    public void getBuses(List<String> params_day_direction_station) throws SQLException {
        dataBase.getBuses(params_day_direction_station);
    }

    @Override
    public void refreshSchedule() {

    }

    @Override
    public Observable<BusScheduleResponse> getBusesResponse() {
        return requestScheduleToPresentationEmitter;
    }

    public Observer<BusScheduleController.ValueLessSignal> getOnRefreshSchedule() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed to refreshSchedule");
            }

            @Override
            public void onNext(BusScheduleController.@NonNull ValueLessSignal valueLessSignal) {
                refreshSchedule();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}