package org.hse_app.database;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.entities.BusScheduleResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final ReplaySubject<List<Bus>> busSchedule = ReplaySubject.create();
    private final ReplaySubject<BusScheduleResponse> busScheduleResponse = ReplaySubject.create();
    Integer REVISION = 1;
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "postgres";
    String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public DataBase() {
    }

    public void getBuses(List<String> params_day_direction_station) throws SQLException {
        int day = Integer.parseInt(params_day_direction_station.get(0));
        String direction = params_day_direction_station.get(1);
        String station = params_day_direction_station.get(2);
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQL_SELECT_BUSES = "select * from bus_schedule where day = " + day + " and direction = '" + direction + "' and station = '" + station + "'";
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_BUSES);
        ArrayList<Bus> buses = new ArrayList<>();
        while (resultSet.next()) {
            buses.add(new Bus(resultSet.getInt("id"), resultSet.getInt("day"), resultSet.getLong("daytime"), resultSet.getString("daytimestring"), resultSet.getString("direction"), resultSet.getString("station")));
        }
        busSchedule.onNext(buses);
        busScheduleResponse.onNext(new BusScheduleResponse(REVISION, buses));
    }

    public void updateBuses() {
        // TODO update db
        ++REVISION;
    }

    public Observable<List<Bus>> getBuseSchedule() {
        return busSchedule;
    }
}
