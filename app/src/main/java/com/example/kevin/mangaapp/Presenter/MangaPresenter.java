package com.example.kevin.mangaapp.Presenter;

import android.util.Log;

import com.example.kevin.mangaapp.Model.ConnectionApi;
import com.example.kevin.mangaapp.Model.MangaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kevin on 12/9/2016.
 */

public class MangaPresenter {
    MangaConnectionResponse mangaConnectionResponse;


    public MangaPresenter (MangaConnectionResponse mangaConnectionResponse){
        this.mangaConnectionResponse = mangaConnectionResponse;
    }

    public void getMangaList() {

        ConnectionApi.getInstance().getAPIModel().getMangaList("1").enqueue(new Callback<MangaResponse>() {
            @Override
            public void onResponse(Call<MangaResponse> call, Response<MangaResponse> response) {
//                if (response.body().getCode().equals("201")) {
                    mangaConnectionResponse.doMangaSuccess(response.body());
                Log.e("Project", "udah OKe0");
//                } else if (response.body().getCode().equals("401")) {
//                    mangaResponse.doMangaFail(response.body().getMessage());
//                }
            }

            @Override
            public void onFailure(Call<com.example.kevin.mangaapp.Model.MangaResponse> call, Throwable t) {
                mangaConnectionResponse.doConnectionError("Sorry, please check your connection.");
            }
        });

    }

//

}
