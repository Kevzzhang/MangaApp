package com.example.davidbezalellaoli.demohome.presenter.users;

import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;

/**
 * Created by David Bezalel Laoli on 12/16/2016.
 */

public interface InterfaceViewUserProfileEdit {
    void doShowEditSuccess (UserResponse user);
    void doShowEditFail (int message);
    void doEditFail(String message);
}