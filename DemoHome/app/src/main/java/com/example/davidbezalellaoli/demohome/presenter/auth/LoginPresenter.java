package com.example.davidbezalellaoli.demohome.presenter.auth;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David Bezalel Laoli on 10/30/2016.
 */
public class LoginPresenter {

    iPresenterResponse loginResponse;

    /*
    * @param interface: iPresenterResponse
    */
    public LoginPresenter (iPresenterResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    /*
    * TO-DO: set data to API
    * TO-DO: set parameter to response
    */
    public void doLogin(String email, String password) {
        Map<String, String> _userdatas = new HashMap<>();
        _userdatas.put("email", email);
        _userdatas.put("password", password);

        ConnectionAPI.getInstance().getAPIModel().doLogin(_userdatas).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getStatus()) {
                    loginResponse.doSuccess(response.body());
                } else {
                    loginResponse.doFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                loginResponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
