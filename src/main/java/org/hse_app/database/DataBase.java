package org.hse_app.database;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.entities.BusScheduleResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final ReplaySubject<BusScheduleResponse> busScheduleResponse = ReplaySubject.create();
    Integer REVISION = 1;
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "postgres";
    String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    String DB_NAME = "bus_schedule";

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
        busScheduleResponse.onNext(new BusScheduleResponse(REVISION, buses));
    }

    public void updateBuses() {
        // TODO update db
        ++REVISION;
    }

    public Observable<BusScheduleResponse> getBusSchedule() {
        return busScheduleResponse;
    }

    private void generateDataForDB() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);//add try catch or all command
            Statement statement = connection.createStatement();
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 10; ++j) {
                    int id = i * 10 + j + 1;
                    int day =  0;
                    int dayTime = 0;
                    String dayTimeString = "";
                    String direction = "";
                    String station = "";
                    day = i + 1;
                    if (j < 5) {
                        dayTime = 3600 * j + 36000;
                        Integer hours = dayTime / 3600;
                        Integer minutes = (dayTime % 3600) / 60;
                        dayTimeString = String.format("%02d:%02d", hours, minutes);
                        direction = "msk";
                    } else {
                        dayTime = 3600 * (j - 5) + 36000;
                        Integer hours = dayTime / 3600;
                        Integer minutes = (dayTime % 3600) / 60;
                        dayTimeString = String.format("%02d:%02d", hours, minutes);
                        direction = "dbk";
                    }
                    if (j % 3 == 0) {
                        station = "mid";
                    } else if (j % 3 == 1) {
                        station = "odn";
                    } else  {
                        station = "slv";
                    }
                    String SQL_INSERT_BUSES = "INSERT INTO " + DB_NAME + " VALUES (" + id + ", " + day + ", " + dayTime + ", '" + dayTimeString + "', '" +
                            direction + "', '" + station + "')";
                    statement.executeUpdate(SQL_INSERT_BUSES);
                }
            }
        }
        catch (Exception e){
            System.err.println("Error");
            System.err.println(e.getMessage());
        }
    }
}
