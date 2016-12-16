package com.example.kevin.mangaapp.Model;

/**
 * Created by Kevin on 12/15/2016.
 */

public class MangaObjectHot {
    private String title;
    private String url;
    private String view;
    private String img;

    public MangaObjectHot(String title, String url, String view, String img){
        this.title = title;
        this.url = url;
        this.view = view;
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
