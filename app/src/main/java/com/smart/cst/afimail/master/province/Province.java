package com.smart.cst.afimail.master.province;

public class Province {
    String id;
    String ProvinceCode;
    String RegionCode;
    String ProvinceName;
    String ProvinceNameDari;
    String ProvinceNamePashtu;

    public Province() {
    }

    public Province(String id,String provinceCode, String regionCode, String provinceName, String provinceNameDari, String provinceNamePashtu) {
        this.id=id;
        ProvinceCode = provinceCode;
        RegionCode = regionCode;
        ProvinceName = provinceName;
        ProvinceNameDari = provinceNameDari;
        ProvinceNamePashtu = provinceNamePashtu;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        ProvinceCode = provinceCode;
    }

    public String getRegionCode() {
        return RegionCode;
    }

    public void setRegionCode(String regionCode) {
        RegionCode = regionCode;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public String getProvinceNameDari() {
        return ProvinceNameDari;
    }

    public void setProvinceNameDari(String provinceNameDari) {
        ProvinceNameDari = provinceNameDari;
    }

    public String getProvinceNamePashtu() {
        return ProvinceNamePashtu;
    }

    public void setProvinceNamePashtu(String provinceNamePashtu) {
        ProvinceNamePashtu = provinceNamePashtu;
    }
}
