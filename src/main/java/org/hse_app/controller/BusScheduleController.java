package org.hse_app.controller;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.repository.BusScheduleModelImpl;

import java.util.ArrayList;

public class BusScheduleController {
    String day;
    String direction;
    String station;
    private Subject<ArrayList<String>> requestScheduleEmitter;
    private BusScheduleModelImpl busesRepository;
    public BusScheduleController() {
        busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        requestScheduleEmitter = BehaviorSubject.<ArrayList<String>>create();
        requestScheduleEmitter.hide().subscribe(busesRepository.getOnRequestShedule());
    }
    public void requestSchedule(String day, String direction, String station) {
        ArrayList<String>  b = new ArrayList<String>(3);
        b.add(day);
        b.add(direction);
        b.add(station);
        //Observable<String> a = Observable.<String>create();
        requestScheduleEmitter.onNext(b);
    }
    private void refreshSchedule() {
        this.busesRepository.refreshSchedule();
    }

}
