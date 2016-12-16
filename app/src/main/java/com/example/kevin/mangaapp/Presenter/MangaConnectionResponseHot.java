package com.example.kevin.mangaapp.Presenter;

import com.example.kevin.mangaapp.Model.MangaResponseHot;

/**
 * Created by Kevin on 12/16/2016.
 */

public interface MangaConnectionResponseHot {
    public void doMangaSuccess(MangaResponseHot mangaResponseHot);
    public void doMangaFail(String message);
    public void doConnectionError (String message);
}
