package com.smart.cst.afimail;

/**
 * Created by Jeeva on 02-Sep-18.
 */

public class SurveyData {

    String id;
    String studentId;
    String data;

    public SurveyData(String id, String studentId, String data) {
        this.id = id;
        this.studentId = studentId;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
