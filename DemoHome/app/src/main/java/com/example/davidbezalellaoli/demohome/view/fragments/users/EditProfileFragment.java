package com.example.davidbezalellaoli.demohome.view.fragments.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.User;
import com.example.davidbezalellaoli.demohome.view.fragments.UserProfileEditDialogFragment;
import com.google.gson.Gson;

/**
 * Created by David Bezalel Laoli on 12/16/2016.
 */

public class EditProfileFragment extends Fragment {

    ImageView ivProfile;
    TextView tvNama,tvEmail,tvbio,tvPassword;
    Button btnDone;
    User user;
    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_profile,container,false);

        user = new Gson().fromJson(getArguments().getString("user"),User.class);
        sessionManager = new SessionManager(getContext());
        initView(v);

        tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowDialogEditFragment("1");
            }
        });

        return v;
    }

    public void initView(View v){
        ivProfile = (ImageView)v.findViewById(R.id.userprofileshow_profilepicture);
        tvNama = (TextView) v.findViewById(R.id.userprofileshow_txtname);
        tvEmail = (TextView) v.findViewById(R.id.userprofileshow_txtemail);
        tvbio = (TextView) v.findViewById(R.id.userprofileshow_txtbio);
        tvPassword = (TextView) v.findViewById(R.id.userprofileshow_txtchangepassword);
        btnDone = (Button) v.findViewById(R.id.userprofileshow_btndone);

        ivProfile.setImageResource(R.drawable.user);
        tvNama.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvbio.setText(user.getBio());

    }

    public static EditProfileFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putString("user",new Gson().toJson(user));
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    void doShowDialogEditFragment (String flag_) {
        FragmentTransaction _ft = getFragmentManager().beginTransaction();
        Fragment _fragment = getFragmentManager().findFragmentByTag("userprofiledialog");
        Bundle _bundle = new Bundle();
        _bundle.putString("EDITEDFLAG", flag_);

        UserProfileEditDialogFragment _userprofiledialogfragment = UserProfileEditDialogFragment.newInstance(_bundle);
        _userprofiledialogfragment.setTargetFragment(this, 1);
        _userprofiledialogfragment.show(_ft, "userprofiledialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        User _user = sessionManager.getUserLoggedIn();
        tvNama.setText(_user.getName());
        tvEmail.setText(_user.getEmail());
        tvbio.setText(_user.getBio());
    }
}
