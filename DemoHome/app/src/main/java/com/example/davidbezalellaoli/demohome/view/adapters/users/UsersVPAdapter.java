package com.example.davidbezalellaoli.demohome.view.adapters.users;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * Created by David Bezalel Laoli on 11/20/2016.
 */

public class UsersVPAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public UsersVPAdapter(FragmentManager fm) {
        super(fm);
    }

    public void doAddFragment (Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
