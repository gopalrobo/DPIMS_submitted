package com.smart.cst.afimail.pra;

/**
 * Created by Lincoln on 15/01/16.
 */
public class Pra {
    private String title, genre, year,url;

    public Pra() {
    }

    public Pra(String title, String genre, String year,String url) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.url=url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
