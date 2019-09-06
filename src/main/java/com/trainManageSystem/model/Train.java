package com.trainManageSystem.model;


public class Train {
    private String train_no;
    private String train_name;
    private String s_plat;
    private String e_plat;
    private int train_id;

    public String getTrain_no() {
        return train_no;
    }

    public String getTrain_code() {
        return train_name;
    }

    public String getS_plat() {
        return s_plat;
    }

    public String getE_plat() {
        return e_plat;
    }
    public int getTrain_id(){return train_id;}

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public void setTrain_code(String train_name) {
        this.train_name = train_name;
    }

    public void setS_plat(String s_plat) {
        this.s_plat = s_plat;
    }

    public void setE_plat(String e_plat) {
        this.e_plat = e_plat;
    }

    public void setTrain_id(int train_id) {this.train_id = train_id;}
}
