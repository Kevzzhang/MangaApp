package com.example.davidbezalellaoli.demohome.model.connections;

import com.example.davidbezalellaoli.demohome.model.controller.APIModel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David Bezalel Laoli on 10/14/2016.
 */
public class ConnectionAPI {
//    private static final String BASEURL = "http://192.168.0.100:8001/demo/public/";
//    private static final String BASEURL = "http://192.168.1.27:8001/demo/public/";
    private static final String BASEURL = "http://192.168.43.119:8001/demo/public/";
    private static ConnectionAPI instance;
    private static APIModel api;
    private static Retrofit retrofit;

    /*
    * @retun Object: ConnectionAPI
    */
    public static ConnectionAPI getInstance() {
        return (instance == null) ? new ConnectionAPI() : instance;
    }

    /*
    * @return object: APIModel
    */
    public APIModel getAPIModel() {
        return api;
    }


    /*
    * TO-DO: build: Retrofit
    * TO-DO: create: APIModel
    */
    private ConnectionAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIModel.class);
    }
}
