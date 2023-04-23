package org.hse_app.presentation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.entities.BusScheduleResponse;
import org.hse_app.model.repository.BusScheduleModelImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class BusSchedulePresentation {
    private final ReplaySubject<String> response = ReplaySubject.create();

    public BusSchedulePresentation() {
        BusScheduleModelImpl busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        Observable<BusScheduleResponse> buses = busesRepository.getBusesResponse();
        buses.subscribe(getBusesObserver());

    }
    public Observable<String> getBuses() {
        return response;
    }

    private Observer<BusScheduleResponse> getBusesObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("useCaseOnSubscribe");
            }

            @Override
            public void onNext(@NonNull BusScheduleResponse busSchedule) {
                try {
                    response.onNext(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).writer().withDefaultPrettyPrinter().writeValueAsString(busSchedule));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
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
}
