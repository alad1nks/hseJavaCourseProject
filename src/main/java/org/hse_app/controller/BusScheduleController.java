package org.hse_app.controller;


import com.sun.net.httpserver.HttpServer;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.repository.BusScheduleModel;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.BusSchedulePresentation;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hse_app.api.ApiUtils.splitQuery;

public class BusScheduleController {
    public enum ValueLessSignal{
        IGNORE_ME;
    }
    private final ReplaySubject<ValueLessSignal> refreshScheduleEmitter = ReplaySubject.create();
    private final ReplaySubject<List<String>> requestScheduleEmitter = ReplaySubject.create();;
    private String busResponse = "";
    public BusScheduleController() throws IOException {
        BusScheduleModel model = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        BusSchedulePresentation presentation = ApplicationContextProvider.getApplicationContext().getBean("BusSchedulePresentationSingleton", BusSchedulePresentation.class);
        Observable<String> buses = presentation.getBuses();
        buses.subscribe(getBusesObserver());
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/buses", (exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
                String noDayText = "1";
                String noDirectionText = "msk";
                String noStationText = "odn";
                String day = params.getOrDefault("day", List.of(noDayText)).stream().findFirst().orElse(noDayText);
                String direction = params.getOrDefault("direction", List.of(noDirectionText)).stream().findFirst().orElse(noDirectionText);
                String station = params.getOrDefault("station", List.of(noStationText)).stream().findFirst().orElse(noStationText);
                try {
                    model.getBuses(List.of(day, direction, station));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String respText = busResponse;
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(busResponse.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        }));
        server.setExecutor(null);
        server.start();
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

    private Observer<String> getBusesObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("presentationOnSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                busResponse = s;
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
