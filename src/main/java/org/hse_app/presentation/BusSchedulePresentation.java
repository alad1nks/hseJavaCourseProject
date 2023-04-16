package org.hse_app.presentation;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.hse_app.ApplicationContextProvider;

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
                presentData(s);
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
    private void presentData(String s) {
        System.out.println(s);
    }
 }
