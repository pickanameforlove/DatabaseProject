package com.trainManageSystem.model;

import java.util.Date;

public class Dingdan {
    private int order_id;
    private String from_station;
    private String to_station;
    private String account;
    private String money;
    private Date date;

    public int getOrder_id() {
        return order_id;
    }

    public String getFrom_station() {
        return from_station;
    }

    public String getTo_station() {
        return to_station;
    }

    public String getAccount() {
        return account;
    }

    public String getMoney() {
        return money;
    }

    public Date getDate() {
        return date;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setFrom_station(String from_station) {
        this.from_station = from_station;
    }

    public void setTo_station(String to_station) {
        this.to_station = to_station;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
