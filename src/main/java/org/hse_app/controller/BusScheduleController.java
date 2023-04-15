package org.hse_app.controller;


import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.repository.BusScheduleModelImpl;

import java.util.ArrayList;

public class BusScheduleController {
    private final Subject<ArrayList<String>> requestScheduleEmitter;
    private final BusScheduleModelImpl busesRepository;
    public BusScheduleController() {
        busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        requestScheduleEmitter = BehaviorSubject.create();
        requestScheduleEmitter.hide().subscribe(busesRepository.getOnRequestSchedule());
    }
    public void requestSchedule(String day, String direction, String station) {
        ArrayList<String> b = new ArrayList<>(3);
        b.add(day);
        b.add(direction);
        b.add(station);
        requestScheduleEmitter.onNext(b);
    }
    private void refreshSchedule() {
        busesRepository.refreshSchedule();
    }

}
