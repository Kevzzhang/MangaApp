package com.example.davidbezalellaoli.demohome.view.fragments.auth;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.example.davidbezalellaoli.demohome.presenter.auth.RegisterPresenter;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.view.activities.AuthActivity;
import com.example.davidbezalellaoli.demohome.view.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements iPresenterResponse {

    /*
    * declare view component
    */
    EditText txtname, txtemail, txtpassword, txtrepassword;
    TextView txtredirecttologin;
    Button btnregister;

    /*
    * declare object
    */
    SessionManager sessionManager;
    Context context;
    RegisterPresenter registerpresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register2, container, false);

        /*
        * initiate view component
        */
        txtname            = (EditText) view.findViewById(R.id.reg_txtname);
        txtemail           = (EditText) view.findViewById(R.id.reg_txtemail);
        txtpassword        = (EditText) view.findViewById(R.id.reg_txtpassword);
        txtrepassword      = (EditText) view.findViewById(R.id.reg_txtrepassword);
        btnregister        = (Button) view.findViewById(R.id.reg_btnregister);
        txtredirecttologin = (TextView) view.findViewById(R.id.reg_txtredirectlogin);

        /*
        * instantiate objct
        */
        context = getContext();
        sessionManager = new SessionManager(context);
        registerpresenter = new RegisterPresenter(this);

        /*
        * view component listener
        */
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (TextUtils.isEmpty(txtname.getText().toString()) || TextUtils.isEmpty(txtemail.getText().toString()) || TextUtils.isEmpty(txtpassword.getText().toString()) || TextUtils.isEmpty(txtrepassword.getText().toString())) {
                Toast.makeText(context, "Please fill the blank fields.", Toast.LENGTH_SHORT).show();
            } else if (!txtpassword.getText().toString().equals(txtrepassword.getText().toString())) {
                Toast.makeText(context, "Password and Re-Password does not match.", Toast.LENGTH_SHORT).show();
            } else {
                registerpresenter.doRegister(txtname.getText().toString(), txtemail.getText().toString(), txtpassword.getText().toString());
            }
            }
        });

        txtredirecttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ((AuthActivity)getActivity()).doChangeFragment(new LoginFragment());
            }
        });

        return view;
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
