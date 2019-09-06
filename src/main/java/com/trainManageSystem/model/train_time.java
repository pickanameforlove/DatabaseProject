package com.trainManageSystem.model;

import java.sql.Time;

public class train_time {
    private  String train_no;
    private String station_name;
    private int station_no;
    private String arrive_time;
    private String start_time;
    private int arrive_day_diff;
    private String running_time;

    public String getTrain_no() {
        return train_no;
    }

    public String getStation_name() {
        return station_name;
    }

    public int getStation_no() {
        return station_no;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public int getArrive_day_diff() {
        return arrive_day_diff;
    }

    public String getRunning_time() {
        return running_time;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setStation_no(int station_no) {
        this.station_no = station_no;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setArrive_day_diff(int arrive_day_diff) {
        this.arrive_day_diff = arrive_day_diff;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }
}
