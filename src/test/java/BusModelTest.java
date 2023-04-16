import org.hse_app.ApplicationContextProvider;
import org.hse_app.SpringConfig;
import org.hse_app.controller.BusScheduleController;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.UseCase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusModelTest {

    @Test
    void CheckSubscribtions() {
        MockitoAnnotations.openMocks(this);
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = Mockito.mock(BusScheduleController.class);
        BusScheduleModelImpl busesRepository = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
        System.out.println(controller);
        applicationContext.close();
    }

    @Test
    void ModelSingltone() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        BusScheduleController controller = new BusScheduleController();
        UseCase useCase = new UseCase();
        useCase.createBusModel();
        Class<?> useCaseReflect = useCase.getClass();
        Field useCaseField = null;
        try {
             useCaseField = useCaseReflect.getDeclaredField("busesRepository");
             useCaseField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            assert(false);
        }
        Class<?> controllerReflect = controller.getClass();
        Field controllerField = null;
        try {
            controllerField = controllerReflect.getDeclaredField("busesRepository");
            controllerField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            assert(false);
        }
        try {
            assertEquals(controllerField.get(controller), useCaseField.get(useCase));
        } catch (IllegalAccessException e) {
            assert(false);
        }
        applicationContext.close();
    }
}
