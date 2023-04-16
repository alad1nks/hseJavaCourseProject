package org.hse_app;

import org.hse_app.controller.BusScheduleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        controller.requestSchedule("1", "msk", "odn");//tempuaryr ignored date
        controller.refreshSchedule();
        applicationContext.close();
    }
}