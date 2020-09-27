package com.smart.cst.afimail.member;

import java.io.Serializable;

public class Member implements Serializable {

    public String memberImage;
    public String memberName;
    public String sex;
    public String mobile;
    public String idType;
    public String farmSize;

    public Member(String memberImage, String memberName, String sex, String mobile, String idType, String farmSize) {
        this.memberImage = memberImage;
        this.memberName = memberName;
        this.sex = sex;
        this.mobile = mobile;
        this.idType = idType;
        this.farmSize = farmSize;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }
}
