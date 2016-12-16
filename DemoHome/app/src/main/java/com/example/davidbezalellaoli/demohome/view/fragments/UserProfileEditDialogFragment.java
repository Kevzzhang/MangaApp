package com.example.davidbezalellaoli.demohome.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.basic.User;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.presenter.users.InterfaceViewUserProfileEdit;
import com.example.davidbezalellaoli.demohome.presenter.users.UserUpdatedPresenter;

/**
 * Created by David Bezalel Laoli on 12/16/2016.
 */

public class UserProfileEditDialogFragment extends DialogFragment implements iPresenterResponse {

    EditText txtfieldedited;
    Button btndone;
    Context context;
    SessionManager sessionmanager;
    String text;

    UserUpdatedPresenter userupdatepresenter;


    public static UserProfileEditDialogFragment newInstance(Bundle bundle_) {
        UserProfileEditDialogFragment userprofiledialogfragment = new UserProfileEditDialogFragment();
        userprofiledialogfragment.setArguments(bundle_);
        return userprofiledialogfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_user_profile_edit_dialog, container, false);

        txtfieldedited = (EditText) _view.findViewById(R.id.userprofileedit_txtfieldedited);
        btndone        = (Button) _view.findViewById(R.id.userprofileedit_btndone);

        context = getContext();
        sessionmanager = new SessionManager(context);
        userupdatepresenter = new UserUpdatedPresenter(this);

        final Bundle _flagedited = getArguments();
        if (_flagedited.get("EDITEDFLAG").equals("1")) {
            txtfieldedited.setText(sessionmanager.getUserLoggedIn().getName());
        }

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_flagedited.get("EDITEDFLAG").equals("1") || _flagedited.get("EDITEDFLAG").equals("2")) {
                    if (TextUtils.isEmpty(txtfieldedited.getText().toString())) {
                        Toast.makeText(context, "Please fill the blank field.", Toast.LENGTH_SHORT).show();
                    } else {
                        userupdatepresenter.doUpdateUserProfile(sessionmanager.getUserLoggedIn().getId(), _flagedited.get("EDITEDFLAG").toString(), txtfieldedited.getText().toString());
                    }
                } else {
                    userupdatepresenter.doUpdateUserProfile(sessionmanager.getUserLoggedIn().getId(), _flagedited.get("EDITEDFLAG").toString(), txtfieldedited.getText().toString());
                }

            }
        });

        return _view;
    }

    @Override
    public void doSuccess(Response response) {
        UserResponse user = (UserResponse) response;
        Toast.makeText(context, user.getMessage(), Toast.LENGTH_SHORT).show();
        sessionmanager.doCreateSession(user.getUser());
        Intent _intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), 1, _intent);
        getDialog().dismiss();
    }

    @Override
    public void doFail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}

