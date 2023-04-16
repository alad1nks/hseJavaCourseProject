package org.hse_app;

import org.hse_app.controller.BusScheduleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        controller.requestSchedule("1", "2", "3");//tempuaryr ignored date

        //busScheduleController.requestSchedule("3", "2", "2");
        controller.refreshSchedule();
        applicationContext.close();
    }
}