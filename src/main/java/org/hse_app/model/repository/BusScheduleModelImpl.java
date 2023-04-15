package org.hse_app.model.repository;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.hse_app.DataBase;
import org.hse_app.model.entities.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusScheduleModelImpl implements BusScheduleModel {
    private DataBase dataBase;
    private final Observer<ArrayList<String>> onRequestSchedule;
    private final Observable<List<Bus>> busesResponse;

    public BusScheduleModelImpl() {
        List<Bus> hardcodedBuses = new ArrayList<>();
        hardcodedBuses.add(new Bus(0, 0, 22222L, "08:30", "msk", "odn"));
        busesResponse = Observable.just(hardcodedBuses); // hardcoded list
        onRequestSchedule = new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed");
            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
                System.out.println(strings);
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

    public Observer<ArrayList<String>> getOnRequestSchedule() {
        return onRequestSchedule;
    }

    @Override
    public void getBuses(String day, String direction, String station) {

    }

    @Override
    public Observable<Integer> getRevision() {
        return null;
    }

    @Override
    public void refreshSchedule() {

    }

    @Override
    public Observable<List<Bus>> getBusesResponse() {
        return busesResponse;
    }
}
