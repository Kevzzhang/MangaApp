package com.example.kevin.mangaapp.Model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 12/8/2016.
 */

public class ConnectionApi {
    public static final String BASE_URL ="http://172.22.228.172:3000/";
    private static Retrofit retrofit = null;
    private static APIModel api;
    private static ConnectionApi instance;

    public static ConnectionApi getInstance() {
        if (instance == null)
            instance =  new ConnectionApi();
        return instance;
    }

    public APIModel getAPIModel() {
        return api;
    }
    private ConnectionApi(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        api = retrofit.create(APIModel.class);
    }
}
