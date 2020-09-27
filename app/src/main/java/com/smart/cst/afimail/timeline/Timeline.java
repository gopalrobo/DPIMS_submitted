package com.smart.cst.afimail.timeline;

public class Timeline {
    String status;
    String time;

    public Timeline() {
    }

    public Timeline(String status) {
        this.status = status;
    }

    public Timeline(String status, String time) {
        this.status = status;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
