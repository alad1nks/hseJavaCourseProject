package org.hse_app.presentation;

import org.hse_app.ApplicationContextProvider;
import org.hse_app.model.repository.BusScheduleModel;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class BusSchedulePresentation {
    private UseCase useCase;

    public BusSchedulePresentation() {
        useCase = ApplicationContextProvider.getApplicationContext().getBean("UseCaseSingleton", UseCase.class);
    }
    public void getSchedule() {

    }
}
