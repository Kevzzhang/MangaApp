package com.example.kevin.mangaapp.Model;

import java.util.List;

/**
 * Created by Kevin on 12/15/2016.
 */

public class MangaResponseHot{
    private String title;
    private List<MangaObjectHot> manga;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MangaObjectHot> getManga() {
        return manga;
    }

    public void setManga(List<MangaObjectHot> manga) {
        this.manga = manga;
    }
}
