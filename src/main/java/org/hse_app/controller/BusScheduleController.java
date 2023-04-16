package org.hse_app.controller;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.ArrayList;
import java.util.List;

public class BusScheduleController {
    public enum ValueLessSignal{
        IGNORE_ME;
    }
    private final ReplaySubject<ValueLessSignal> refreshScheduleEmitter = ReplaySubject.create();
    private final ReplaySubject<List<String>> requestScheduleEmitter = ReplaySubject.create();;
    public BusScheduleController() {
    }

    public Observable<List<String>> getRequestScheduleEmitter() {
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
