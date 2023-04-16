package org.hse_app.model.repository;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.database.DataBase;
import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.entities.Bus;

import java.sql.SQLException;
import java.util.List;

public class BusScheduleModelImpl implements BusScheduleModel {
    private final DataBase dataBase;
    private final Observable<List<Bus>> requestScheduleToPresentationEmitter;

    public BusScheduleModelImpl() {
        dataBase = new DataBase();
        requestScheduleToPresentationEmitter = dataBase.getBuseSchedule();
        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        Observable<List<String>> controllerRequestScheduleEmitter = controller.getRequestScheduleEmitter();
        Observable<BusScheduleController.ValueLessSignal> controllerRefreshScheduleEmitter = controller.getRefreshScheduleEmitter();
        controllerRequestScheduleEmitter.subscribe(getOnRequestSchedule());
        controllerRefreshScheduleEmitter.subscribe(getOnRefreshSchedule());
    }

    @Override
    public void getBuses(List<String> params_day_direction_station) throws SQLException {
        dataBase.getBuses(params_day_direction_station);
    }

    @Override
    public void refreshSchedule() {

    }

    @Override
    public Observable<List<Bus>> getBusesResponse() {
        return requestScheduleToPresentationEmitter;
    }

    private Observer<List<String>> getOnRequestSchedule() {
        return new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed to request schedule");
            }

            @Override
            public void onNext(@NonNull List<String> strings) {
                try {
                    getBuses(strings);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        };
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