package com.example.davidbezalellaoli.demohome.model.controller;

import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by David Bezalel Laoli on 10/14/2016.
 */
public interface APIModel {

    /*
    * this interface is your model based on your routes in API
    */
    @POST("user/register")
    Call<UserResponse> doRegister (@Body Map<String, String> userdata);

    @POST("user/login")
    Call<UserResponse> doLogin (@Body Map<String, String> userdata);

    @POST("users/all/{id}")
    Call<UsersResponse> getAllUsers (@Path("id") String id, @Body Map<String, String> filter);

    @POST("follow/{id}/{followingid}")
    Call<Response> dofollow (@Path("id") String id, @Path("followingid") String followingid);

    @GET("follow/getfollowing/{id}")
    Call<UsersResponse> getfollowingusers (@Path("id") String id);

    @GET("follow/getfollowers/{id}")
    Call<UsersResponse> getfollowers (@Path("id") String id);

    @POST("user/update/{id}")
    Call<UserResponse> doUpdate (@Path("id") String id, @Body Map<String, String> userdata);

}
