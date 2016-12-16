package com.example.davidbezalellaoli.demohome.view.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.view.fragments.auth.LoginFragment;

public class AuthActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);
        doChangeFragment(new LoginFragment());
        this.setTitle("Authentication");
    }

    public void doChangeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_activity_container, fragment).commit();
    }
}
