package com.example.kevin.mangaapp.Model;

import java.util.List;

/**
 * Created by Kevin on 12/8/2016.
 */

public class MangaResponse {
    private String title;
    private List<MangaObject> manga;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MangaObject> getManga() {
        return manga;
    }

    public void setManga(List<MangaObject> manga) {
        this.manga = manga;
    }
}
