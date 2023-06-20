import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.ApplicationContextProvider;
import org.hse_app.SpringConfig;
import org.hse_app.database.DataBase;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.entities.BusRequest;
import org.hse_app.model.repository.BusScheduleModelImpl;
import org.hse_app.presentation.BusSchedulePresentation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class BusModelTest {

    @Test
    void checkGetSQLUpdateCommand() {
        DataBase db = new DataBase();
        BusRequest b = new BusRequest();
        b.setDay(1);
        b.setDirection("msk");
        b.setDayTimeString("08:30");
        String a = db.getSQLUpdateCommand(b, 0);
        String station = "odn";
        String direction = "msk";
        String dayTimeString = "08:30";
        int day = 1;
        long dayTime = Long.parseLong(dayTimeString.substring(0, 2)) * 3600000 + Long.parseLong(dayTimeString.substring(3, 5)) * 60000;
        Assert.assertEquals(a, "INSERT INTO bus_schedule VALUES (0" + ", " + day + ", " + dayTime + ", '" + dayTimeString + "', '" +
                direction + "', '" + station + "')");
    }

}
