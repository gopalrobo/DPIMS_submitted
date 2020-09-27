package com.smart.cst.afimail.plan;

import java.io.Serializable;

public class Plan implements Serializable {
    String id;
    String activity;
    String geotag;
    String status;
    String level;
    String sector;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getGeotag() {
        return geotag;
    }

    public void setGeotag(String geotag) {
        this.geotag = geotag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Plan(String id, String activity, String geotag, String status) {
        this.id = id;
        this.activity = activity;
        this.geotag = geotag;
        this.status = status;
    }
}
