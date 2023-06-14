import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.SpringConfig;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.BusSchedulePresentation;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class BusModelTest {

    @Test
    void CheckPresentationSubscription() {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(SpringConfig.class);
//        BusSchedulePresentation presentation = ApplicationContextProvider.getApplicationContext().getBean("BusSchedulePresentationSingleton", BusSchedulePresentation.class);
//        Observable<String> buses = ReplaySubject.create();
//        Class<?> presentationReflect = presentation.getClass();
//        Method presentationMethod = null;
//        Class<?> empty;
//        try {
//            presentationMethod = presentationReflect.getDeclaredMethod("getBusesObserver", (Class<?>[])null);
//            presentationMethod.setAccessible(true);
//            buses.subscribe((Observer<String>)presentationMethod.invoke(presentation));
//        } catch (NoSuchMethodException e) {
//            assert (false);
//        } catch (InvocationTargetException e) {
//            assert (false);
//        } catch (IllegalAccessException e) {
//            assert (false);
//        }
//        buses.test().hasSubscription();
//        applicationContext.close();
    }

    @Test
    void CheckModelSubscription() {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(SpringConfig.class);
//        BusScheduleModelImpl model = ApplicationContextProvider.getApplicationContext().getBean("BusesRepositoryImplSingleton", BusScheduleModelImpl.class);
//        Observable<ArrayList<Bus>> buses = ReplaySubject.create();
//        Class<?> modelReflect = model.getClass();
//        Method modelMethod = null;
//        Class<?> empty;
//        try {
//            modelMethod = modelReflect.getDeclaredMethod("getBusesResponse", (Class<?>[])null);
//            modelMethod.setAccessible(true);
//            buses.subscribe((Observer<ArrayList<Bus>>)modelMethod.invoke(model));
//        } catch (NoSuchMethodException e) {
//            assert (false);
//        } catch (InvocationTargetException e) {
//            assert (false);
//        } catch (IllegalAccessException e) {
//            assert (false);
//        }
//        buses.test().hasSubscription();
//        applicationContext.close();
    }

}
