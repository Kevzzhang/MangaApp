package com.example.davidbezalellaoli.demohome.presenter.users;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.connections.ConnectionAPI;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David Bezalel Laoli on 12/16/2016.
 */

public class UserUpdatedPresenter {

    iPresenterResponse updateResponse;
    SessionManager sessionmanager;

    public UserUpdatedPresenter(iPresenterResponse updateResponse) {
        this.updateResponse = updateResponse;
    }

    public void doUpdateUserProfile(String id, String flag, String updatedvalue) {
        Map<String, String> _updateduser = new HashMap<>();
        _updateduser.put("id", id);
        _updateduser.put("flag", flag);

        _updateduser.put("updatedvalue", updatedvalue);
        ConnectionAPI.getInstance().getAPIModel().doUpdate(id, _updateduser).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getStatus()) {
                    updateResponse.doSuccess(response.body());
                }
                else {
                    updateResponse.doFail(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                updateResponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
//
//    public void doUpdatePassword (String id, String updatedvalue, String currentvalue) {
//        Map<String, String> _updateduser = new HashMap<>();
//        _updateduser.put("id", id);
//        _updateduser.put("flag", "4");
//        _updateduser.put("updatedvalue", updatedvalue);
//        _updateduser.put("currentvalue", currentvalue);
//
//        ConnectivitySingleton.getInstance().api.doUpdateUser(_updateduser).enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if (response.body().getCode().equals("201")) {
//                    ivuserprofileedit.doShowEditSuccess(response.body());
//                } else if (response.body().getCode().equals("400")) {
//                    ivuserprofileedit.doEditFail(response.body().getMessage());
//                }
//            }

//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                ivuserprofileedit.doShowEditFail(R.string.connection_error);
//            }
//        });

//    }
//}

