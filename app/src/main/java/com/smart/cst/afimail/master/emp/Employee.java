package com.smart.cst.afimail.master.emp;

public class Employee {
    String id;
    String fullName;
    String empId;
    String mobile;
    String email;
    String empHistory;
    String pastDesignation;
    String pastDutyStation;
    String presentDesignation;
    String presentDutyStation;
    String password;




    public Employee() {
    }

    public Employee(String id, String fullName, String empId, String mobile, String email, String empHistory, String pastDesignation, String pastDutyStation, String prsentDesignation, String presentDutyStation, String password) {
        this.id = id;
        this.fullName = fullName;
        this.empId = empId;
        this.mobile = mobile;
        this.email = email;
        this.empHistory = empHistory;
        this.pastDesignation = pastDesignation;
        this.pastDutyStation = pastDutyStation;
        this.presentDesignation = prsentDesignation;
        this.presentDutyStation = presentDutyStation;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpHistory() {
        return empHistory;
    }

    public void setEmpHistory(String empHistory) {
        this.empHistory = empHistory;
    }

    public String getPastDesignation() {
        return pastDesignation;
    }

    public void setPastDesignation(String pastDesignation) {
        this.pastDesignation = pastDesignation;
    }

    public String getPastDutyStation() {
        return pastDutyStation;
    }

    public void setPastDutyStation(String pastDutyStation) {
        this.pastDutyStation = pastDutyStation;
    }

    public String getPresentDesignation() {
        return presentDesignation;
    }

    public void setPresentDesignation(String prsentDesignation) {
        this.presentDesignation = prsentDesignation;
    }

    public String getPresentDutyStation() {
        return presentDutyStation;
    }

    public void setPresentDutyStation(String presentDutyStation) {
        this.presentDutyStation = presentDutyStation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
