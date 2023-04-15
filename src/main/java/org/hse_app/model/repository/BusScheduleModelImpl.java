package org.hse_app.model.repository;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.DataBase;
import org.hse_app.presentation.BusSchedulePresentation;
import org.hse_app.presentation.UseCase;
import org.springframework.context.annotation.Lazy;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class BusScheduleModelImpl implements BusScheduleModel {
    private UseCase useCase;
    private DataBase dataBase;
    private Observer<ArrayList<String>> onRequestShedule;
    public BusScheduleModelImpl() {
        useCase = ApplicationContextProvider.getApplicationContext().getBean("UseCaseSingleton", UseCase.class);
        onRequestShedule = new Observer<ArrayList<String>>() {

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

    public Observer<ArrayList<String>> getOnRequestShedule() {
        return onRequestShedule;
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
}
