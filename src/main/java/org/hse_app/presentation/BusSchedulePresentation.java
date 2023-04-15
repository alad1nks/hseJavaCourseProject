package org.hse_app.presentation;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.repository.BusScheduleModel;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class BusSchedulePresentation {

    public BusSchedulePresentation() {
        UseCase useCase = ApplicationContextProvider.getApplicationContext().getBean("UseCaseSingleton", UseCase.class);
        Observable<String> buses = useCase.getBuses();
        buses.subscribe(getBusesObserver());
    }
    private Observer<String> getBusesObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("presentationOnSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("presentationOnError");
            }

            @Override
            public void onComplete() {
                System.out.println("presentationOnComplete");
            }
        };
    }
}
