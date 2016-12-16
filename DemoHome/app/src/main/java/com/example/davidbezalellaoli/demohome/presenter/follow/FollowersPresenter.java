package com.example.davidbezalellaoli.demohome.presenter.follow;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David Bezalel Laoli on 12/2/2016.
 */

public class FollowersPresenter {
    iPresenterResponse followersresponse;

    public FollowersPresenter (iPresenterResponse followersresponse) {
        this.followersresponse = followersresponse;
    }

    public void getfollowers (String id) {
        ConnectionAPI.getInstance().getAPIModel().getfollowers(id).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        followersresponse.doSuccess(response.body());
                    } else {
                        followersresponse.doFail(response.body().getMessage());
                    }
                } else {
                    followersresponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                followersresponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
