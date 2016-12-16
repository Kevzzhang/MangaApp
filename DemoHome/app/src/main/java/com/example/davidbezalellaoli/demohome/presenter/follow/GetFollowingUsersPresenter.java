package com.example.davidbezalellaoli.demohome.presenter.follow;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David Bezalel Laoli on 12/1/2016.
 */

public class GetFollowingUsersPresenter {
    iPresenterResponse followingpresenter;

    public GetFollowingUsersPresenter (iPresenterResponse followingpresenter) {
        this.followingpresenter = followingpresenter;
    }

    public void getfollowingusers (String id) {
        ConnectionAPI.getInstance().getAPIModel().getfollowingusers(id).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus()) {
                        followingpresenter.doSuccess(response.body());
                    } else {
                        followingpresenter.doFail(response.body().getMessage());
                    }
                } else {
                    followingpresenter.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                followingpresenter.doConnectionError(R.string.connection_error);
            }
        });
    }
}
