package org.hse_app.presentation;

import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.repository.BusScheduleModelImpl;

public class UseCase {
    private BusScheduleModelImpl busesRepository;
    public UseCase() {
        //busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);

    }
    public void setter(){
        busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);

    }


}
