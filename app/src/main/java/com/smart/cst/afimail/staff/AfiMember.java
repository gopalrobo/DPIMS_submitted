package com.smart.cst.afimail.staff;

public class AfiMember {
    String id;
    String state;
    String district;
    String province;
    String extension;
    String designation;
    String name;
    String contact;
    String email;
    String password;
    String authtoken;

    public AfiMember() {
    }

    public AfiMember(String id, String state, String district, String province, String extension, String designation, String name, String contact, String email, String password, String authtoken) {
        this.id = id;
        this.state = state;
        this.district = district;
        this.province = province;
        this.extension = extension;
        this.designation = designation;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.authtoken = authtoken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
