package com.smart.cst.afimail.master;

public class MasterItem {

    String title;
    int imgId;
    String url;

    public MasterItem(String title, int imgId, String url) {
        this.title = title;
        this.imgId = imgId;
        this.url=url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MasterItem() {
    }
}
