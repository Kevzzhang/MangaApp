package com.example.davidbezalellaoli.demohome.view.fragments.users;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.view.activities.SearchActivity;
import com.example.davidbezalellaoli.demohome.view.adapters.users.UsersVPAdapter;
import com.example.davidbezalellaoli.demohome.view.fragments.auth.LoginFragment;
import com.example.davidbezalellaoli.demohome.view.fragments.auth.RegisterFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersVPContainerFragment extends Fragment {

    ViewPager viewpager;
    TabLayout tabLayout;

    UsersVPAdapter adapter;

    public UsersVPContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_users_vpcontainer, container, false);

        viewpager = (ViewPager) _view.findViewById(R.id.usersvpcontainer_viewpager);
        tabLayout = (TabLayout) _view.findViewById(R.id.usersvpcontainer_tablayout);

        adapter = new UsersVPAdapter(getChildFragmentManager());

        adapter.doAddFragment(new UsersFragment(), "ALL");
        adapter.doAddFragment(new FollowingFragment(), "Following");
        adapter.doAddFragment(new FollowersFragment(), "Followers");

        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);

        return _view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
