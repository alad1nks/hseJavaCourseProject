package org.hse_app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.entities.BusScheduleRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) throws JsonProcessingException {
        String json = """
            { "busSchedule": [{"day":1111, "dayTimeString":"Strong", "direction":"Belwas"}]}
            """;
        ObjectMapper objectMapper = new ObjectMapper();
        BusScheduleRequest studentDto = objectMapper.readValue(json, BusScheduleRequest.class);
        System.out.println(studentDto.getBusSchedule().get(0).getDay());
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = ApplicationContextProvider.getApplicationContext().getBean("BusesScheduleControllerSingleton", BusScheduleController.class);
        controller.requestSchedule("1", "msk", "odn");
        controller.refreshSchedule();
        applicationContext.close();
    }
}