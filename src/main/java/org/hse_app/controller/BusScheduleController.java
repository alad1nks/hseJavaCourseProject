package org.hse_app.controller;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.ArrayList;

public class BusScheduleController {
    //private final BusScheduleModelImpl busesRepository;
    String day;
    String direction;
    String station;
    public enum ValueLessSignal{
        IGNORE_ME;
    }
    private final ReplaySubject<ValueLessSignal> refreshScheduleEmitter = ReplaySubject.<ValueLessSignal>create();
    private final ReplaySubject<ArrayList<String>> requestScheduleEmitter = ReplaySubject.<ArrayList<String>>create();;
    public BusScheduleController() {
        //busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);;
    }

    public Observable<ArrayList<String>> getRequestScheduleEmitter() {
        return requestScheduleEmitter;
    }
    public Observable<ValueLessSignal> getRefreshScheduleEmitter() {
        return refreshScheduleEmitter;
    }
    public void requestSchedule(String day, String direction, String station) {
        ArrayList<String> b = new ArrayList<>(3);
        b.add(day);
        b.add(direction);
        b.add(station);
        requestScheduleEmitter.onNext(b);
    }
    public void refreshSchedule() {
        refreshScheduleEmitter.onNext(ValueLessSignal.IGNORE_ME);
    }

}
