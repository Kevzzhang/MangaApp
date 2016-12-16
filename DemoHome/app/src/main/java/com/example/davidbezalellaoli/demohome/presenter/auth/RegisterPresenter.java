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
 * Created by David Bezalel Laoli on 10/21/2016.
 */
public class RegisterPresenter {

    iPresenterResponse registerresponse;

    /*
    * @param interface: iPresenterUserReponse
    */
    public RegisterPresenter(iPresenterResponse registerresponse) {
        this.registerresponse = registerresponse;
    }

    /*
    * TO-DO: sent data to API
    * TO-DO: set paramater to  function
    */
    public void doRegister(String name, String email, String password) {
        Map<String, String> _userdatas = new HashMap<>();
        _userdatas.put("name", name);
        _userdatas.put("email", email);
        _userdatas.put("password", password);

        ConnectionAPI.getInstance().getAPIModel().doRegister(_userdatas).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getStatus()) {
                    registerresponse.doSuccess(response.body());
                } else  {
                    registerresponse.doFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                registerresponse.doConnectionError(R.string.connection_error);
            }
        });

    }
}
