package com.smart.cst.afimail.staff;

/**
 * Created by user_1 on 12-07-2018.
 */

public class Farmer {
    String id;
    String name;
    String contact;
    String geotags;
    String province;
    String district;
    String village;
    String frontviewUrl;
    String backviewUrl;
    String password;
    String image;
    String gmail;
    public String regid;


    public Farmer() {
    }

    public Farmer(String id, String name, String contact,String geotags, String province, String district, String village, String frontviewUrl, String backviewUrl, String password, String image, String regid) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.geotags = geotags;
        this.province = province;
        this.district = district;
        this.village = village;
        this.frontviewUrl = frontviewUrl;
        this.backviewUrl = backviewUrl;
        this.password = password;
        this.image = image;
        this.regid = regid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeotags() {
        return geotags;
    }

    public void setGeotags(String geotags) {
        this.geotags = geotags;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getFrontviewUrl() {
        return frontviewUrl;
    }

    public void setFrontviewUrl(String frontviewUrl) {
        this.frontviewUrl = frontviewUrl;
    }

    public String getBackviewUrl() {
        return backviewUrl;
    }

    public void setBackviewUrl(String backviewUrl) {
        this.backviewUrl = backviewUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
