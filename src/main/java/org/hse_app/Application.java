package org.hse_app;

import org.hse_app.controller.BusScheduleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        controller.requestSchedule("1", "msk", "odn");
        controller.refreshSchedule();
        applicationContext.close();
    }
}