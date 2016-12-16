package com.example.kevin.mangaapp.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kevin on 12/8/2016.
 */

public interface APIModel {
    @GET("api/latest/{id}")
    Call<MangaResponse> getMangaList(@Path("id") String user_id);

    @GET("api/hot/{id}")
    Call<MangaResponseHot> getMangaHot(@Path("id") String user_id);

}
