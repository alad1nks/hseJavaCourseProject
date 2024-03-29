package org.hse_app;

import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.BusSchedulePresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
@ComponentScan("org.hse_app")
public class SpringConfig {
    @Bean
    @Scope("singleton")
    public BusScheduleModelImpl BusesRepositoryImplSingleton() {
        return new BusScheduleModelImpl();
    }
    @Bean
    @Scope("singleton")
    public BusScheduleController BusesScheduleControllerSingleton() throws IOException {
        return new BusScheduleController();
    }

    @Bean
    @Scope("singleton")
    public BusSchedulePresentation BusSchedulePresentationSingleton() {
        return new BusSchedulePresentation();
    }

}
