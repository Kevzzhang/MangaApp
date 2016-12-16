package com.example.kevin.mangaapp.Model;

/**
 * Created by Kevin on 12/5/2016.
 */

public class MangaObject {
    private String title;
    private String url;
    private String lastupdate;
    private String img;

    public MangaObject(){

    }

    public MangaObject(String title, String url, String lastupdate){
        this.title = title;
        this.url = url;
        this.lastupdate = lastupdate;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
