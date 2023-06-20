package org.hse_app.database;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.hse_app.model.entities.Bus;
import org.hse_app.model.entities.BusRequest;
import org.hse_app.model.entities.BusScheduleResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataBase {
    private final ReplaySubject<BusScheduleResponse> busScheduleResponse = ReplaySubject.create();
    Integer BUS_REVISION = 1;
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "postgres";
    String DB_URL = "jdbc:postgresql://" + System.getenv("DB_CONTAINER") + ":5432/postgres";

    public DataBase() {
    }

    public void getBuses(List<String> params_day_direction_station) throws SQLException {
        int day = Integer.parseInt(params_day_direction_station.get(0));
        String direction = params_day_direction_station.get(1);
        String station = params_day_direction_station.get(2);
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQL_SELECT_BUSES;
        if (!Objects.equals(station, "all")) {
            SQL_SELECT_BUSES = "select * from bus_schedule where day = " + day + " and direction = '" + direction + "' and station = '" + station + "'";
        } else {
            SQL_SELECT_BUSES = "select * from bus_schedule where day = " + day + " and direction = '" + direction + "'";
        }
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_BUSES);
        ArrayList<Bus> buses = new ArrayList<>();
        while (resultSet.next()) {
            buses.add(new Bus(resultSet.getInt("id"), resultSet.getInt("day"), resultSet.getLong("daytime"), resultSet.getString("daytimestring"), resultSet.getString("direction"), resultSet.getString("station")));
        }
        busScheduleResponse.onNext(new BusScheduleResponse(BUS_REVISION, buses));
    }

    public String getSQLUpdateCommand(BusRequest bus, int i) {
        int day = bus.getDay();
        String dayTimeString;
        String direction = bus.getDirection();
        String timeString = bus.getDayTimeString();
        String station;
        if (timeString.length() == 5) {
            dayTimeString = timeString;
            station = "odn";
        } else {
            if (timeString.charAt(timeString.length() - 1) == '"') {
                station = "mld";
            } else {
                station = "slv";
            }
            dayTimeString = timeString.substring(0, 5);
        }
        long dayTime = Long.parseLong(dayTimeString.substring(0, 2)) * 3600000 + Long.parseLong(dayTimeString.substring(3, 5)) * 60000;
        String SQL_INSERT_BUSES = "INSERT INTO bus_schedule VALUES (" + i + ", " + day + ", " + dayTime + ", '" + dayTimeString + "', '" +
                direction + "', '" + station + "')";
        return SQL_INSERT_BUSES;
    }

    public void updateBuses(List<BusRequest> busList) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQL_DELETE_BUSES = "DELETE FROM bus_schedule";
        statement.executeUpdate(SQL_DELETE_BUSES);
        for (int i = 0; i < busList.size(); ++i) {
            BusRequest bus = busList.get(i);
            String SQL_INSERT_BUSES = getSQLUpdateCommand(bus, i);
            System.out.println(SQL_INSERT_BUSES);
            statement.executeUpdate(SQL_INSERT_BUSES);
        }
        ++BUS_REVISION;
    }

    public Observable<BusScheduleResponse> getBusSchedule() {
        return busScheduleResponse;
    }

    private void generateDataForDB() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
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
                    String SQL_INSERT_BUSES = "INSERT INTO bus_schedule VALUES (" + id + ", " + day + ", " + dayTime + ", '" + dayTimeString + "', '" +
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
