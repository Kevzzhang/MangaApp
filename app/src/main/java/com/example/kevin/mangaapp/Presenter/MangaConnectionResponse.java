package com.example.kevin.mangaapp.Presenter;

import com.example.kevin.mangaapp.Model.MangaResponse;

/**
 * Created by Kevin on 12/9/2016.
 */

public interface MangaConnectionResponse {
    public void doMangaSuccess(MangaResponse mangaResponse);
    public void doMangaFail(String message);
    public void doConnectionError (String message);
}
