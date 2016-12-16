package com.example.davidbezalellaoli.demohome.view.fragments.auth;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.presenter.auth.LoginPresenter;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.view.activities.AuthActivity;
import com.example.davidbezalellaoli.demohome.view.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements iPresenterResponse {

    /*
    * delcare view component
    */
    TextView txtredirectregister;
    EditText txtemail, txtpassword;
    Button btnlogin;

    /*
    * declare object
    */
    SessionManager sessionManager;
    Context context;
    LoginPresenter loginpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_login2, container, false);

        /*
        * initiate all the view component
        */
        txtredirectregister = (TextView) _view.findViewById(R.id.login_txtredirectregister);
        txtemail    = (EditText) _view.findViewById(R.id.login_txtemail);
        txtpassword = (EditText) _view.findViewById(R.id.login_txtpassword);
        btnlogin    = (Button) _view.findViewById(R.id.login_btnlogin);

        /*
        * instantiate all the object
        */
        context = getContext();
        sessionManager = new SessionManager(context);
        loginpresenter = new LoginPresenter(this);

        /*
        * view component listener
        */
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginpresenter.doLogin(txtemail.getText().toString(), txtpassword.getText().toString());
            }
        });

        txtredirectregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AuthActivity)getActivity()).doChangeFragment(new RegisterFragment());

            }
        });

        return _view;
    }

    @Override
    public void doSuccess(Response response) {
        UserResponse _userResponse = (UserResponse) response;
        sessionManager.doCreateSession(_userResponse.getUser());
        Toast.makeText(context, _userResponse.getMessage(), Toast.LENGTH_SHORT).show();
        AuthActivity.doChangeActivity(context, MainActivity.class);
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
