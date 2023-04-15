package org.hse_app;

import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.BusSchedulePresentation;
import org.hse_app.presentation.UseCase;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController busScheduleController = new BusScheduleController();
        busScheduleController.requestSchedule("1", "2", "3");
        busScheduleController.requestSchedule("3", "2", "2");
        applicationContext.close();
    }
}