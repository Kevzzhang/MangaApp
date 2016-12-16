package com.example.davidbezalellaoli.demohome.presenter.follow;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by David Bezalel Laoli on 12/1/2016.
 */

public class FollowPresenter {
    iPresenterResponse followresponse;

    public FollowPresenter (iPresenterResponse followresponse) {
        this.followresponse = followresponse;
    }

    public void dofollow (String id, String followingid) {
        ConnectionAPI.getInstance().getAPIModel().dofollow(id, followingid).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        followresponse.doSuccess(response.body());
                    } else {
                        followresponse.doFail(response.body().getMessage());
                    }
                } else {
                    followresponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                followresponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
