package com.trainManageSystem.model;

import java.util.Date;

public class Ticket {
    private int ticket_id;
    private String train_no;
    private String from_station;
    private String to_station;
    private String seat_type;
    private String id;
    private String price;
    private Date date;
    private String start_time;
    private String end_time;
    private int arrive_day_diff;
    private int carriage_id;
    private int seat_number;
    private int order_id;

    public int getTicket_id() {
        return ticket_id;
    }

    public String getTrain_no() {
        return train_no;
    }

    public String getFrom_station() {
        return from_station;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTo_station() {
        return to_station;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public String getPrice() {
        return price;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getArrive_day_diff() {
        return arrive_day_diff;
    }

    public int getCarriage_id() {
        return carriage_id;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFrom_station(String from_station) {
        this.from_station = from_station;
    }

    public void setTo_station(String to_station) {
        this.to_station = to_station;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setArrive_day_diff(int arrive_day_diff) {
        this.arrive_day_diff = arrive_day_diff;
    }

    public void setCarriage_id(int carriage_id) {
        this.carriage_id = carriage_id;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
