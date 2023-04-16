package org.hse_app.presentation;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.repository.BusScheduleModelImpl;

import java.util.ArrayList;
import java.util.List;

public class UseCase {
    private final ReplaySubject<String> response = ReplaySubject.create();

    public UseCase() {
        BusScheduleModelImpl busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        Observable<List<Bus>> buses = busesRepository.getBusesResponse();
        buses.subscribe(getBusesObserver());

    }
    public Observable<String> getBuses() {
        return response;
    }

    private Observer<List<Bus>> getBusesObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("useCaseOnSubscribe");
            }

            @Override
            public void onNext(@NonNull List<Bus> buses) {
                response.onNext(buses.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("useCaseOnComplete");
            }
        };
    }
    public void sendBusesToPresentation(ArrayList<Bus> buses){
        System.out.println(buses);
    }


}
