package com.example.davidbezalellaoli.demohome.presenter.users;

import android.util.Log;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David Bezalel Laoli on 11/20/2016.
 */

public class GetUsersPresenter {

    iPresenterResponse getusersresponse;

    public GetUsersPresenter (iPresenterResponse getusersresponse) {
        this.getusersresponse = getusersresponse;
    }

    public void getAllUsers (String id, String filter) {
        Map<String, String> _filter = new HashMap<>();
        _filter.put("filter", filter);
        ConnectionAPI.getInstance().getAPIModel().getAllUsers(id, _filter).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        getusersresponse.doSuccess(response.body());
                    } else {
                        getusersresponse.doFail(response.body().getMessage());
                    }
                } else {
                    getusersresponse.doFail("Warning");
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                getusersresponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
