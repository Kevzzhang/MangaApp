package com.example.kevin.mangaapp.Presenter;

import com.example.kevin.mangaapp.Model.ConnectionApi;
import com.example.kevin.mangaapp.Model.MangaResponseHot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kevin on 12/16/2016.
 */

public class MangaPresenterHot {
    MangaConnectionResponseHot mangaConnectionResponseHot;

    public MangaPresenterHot(MangaConnectionResponseHot mangaConnectionResponseHot){
        this.mangaConnectionResponseHot  = mangaConnectionResponseHot;
    }

    public void getMangaHot() {

        ConnectionApi.getInstance().getAPIModel().getMangaHot("1").enqueue(new Callback<MangaResponseHot>() {
            @Override
            public void onResponse(Call<MangaResponseHot> call, Response<MangaResponseHot> response) {
                mangaConnectionResponseHot.doMangaSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MangaResponseHot> call, Throwable t) {
                mangaConnectionResponseHot.doConnectionError("Sorry, please check your connection.");
            }
        });
    }
}
