package org.hse_app.model.repository;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.DataBase;
import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.entities.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusScheduleModelImpl implements BusScheduleModel {
    private DataBase dataBase;
    private final ReplaySubject<ArrayList<Bus>> requestScheduleToPresentationEmitter = ReplaySubject.create();

    private int revision;
    private ArrayList<Bus> buses;

    public BusScheduleModelImpl() {
        getBusesFromFile();

        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        Observable<ArrayList<String>> controllerRequestScheduleEmitter = controller.getRequestScheduleEmitter();
        Observable<BusScheduleController.ValueLessSignal> controllerRefreshScheduleEmitter = controller.getRefreshScheduleEmitter();
        controllerRequestScheduleEmitter.subscribe(getOnRequestShedule());
        controllerRefreshScheduleEmitter.subscribe(getOnRefreshSchedule());
    }

//    public Observer<ArrayList<String>> getOnRequestSchedule() {
//        return onRequestSchedule;
//    }

    private void getBusesFromFile() {
        //TODO - Get revision and list of buses from file on device
        revision = 1;
        buses = new ArrayList<Bus>();
        buses.add(new Bus(0, 0, 22222L, "08:30", "msk", "odn"));
        buses.add(new Bus(1, 0, 22322L, "09:30", "msk", "odn"));
        buses.add(new Bus(2, 0, 22422L, "10:30", "msk", "odn"));
        buses.add(new Bus(3, 0, 22522L, "11:30", "msk", "odn"));
        buses.add(new Bus(4, 0, 22222L, "08:30", "odn", "msk"));
        buses.add(new Bus(5, 0, 22322L, "09:30", "odn", "msk"));
        buses.add(new Bus(6, 0, 22422L, "10:30", "odn", "msk"));
        buses.add(new Bus(7, 0, 22522L, "11:30", "odn", "msk"));
    }

    private Observer<ArrayList<String>> getOnRequestShedule() {
        return new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed to request schedule");
            }

            @Override
            public void onNext(@NonNull ArrayList<String> strings) {
                getBuses(strings);
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
        return new Observer<BusScheduleController.ValueLessSignal>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed to refreshShedule");
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

    @Override
    public void getBuses(ArrayList<String> params_day_direction_station) {
        if (!checkRevision()) {
            //TODO - check device is online
            if (true) {
                updateBuses();
            }
        }
        //TODO - add filter based on params
        requestScheduleToPresentationEmitter.onNext(buses);

    }

    //TODO - update buses list
    private void updateBuses() {

    }

    private boolean checkRevision() {
        //TODO - get revision from database and check with local
        getRevision();
        return revision == 1;
    }

    @Override
    public Observable<Integer> getRevision() {
        //TODO - send request to database
        return null;
    }

    @Override
    public void refreshSchedule() {
        getRevision();
    }

    @Override
    public Observable<ArrayList<Bus>> getBusesResponse() {
        return requestScheduleToPresentationEmitter;
    }
}