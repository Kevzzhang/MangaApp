package com.example.davidbezalellaoli.demohome.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.view.fragments.users.UsersVPContainerFragment;

import org.w3c.dom.Text;

public class MainActivity extends ParentActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Context context;
    TextView txtname, txtemail;
    SessionManager sesssionManager;
    LinearLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main,drawer,false);
        navigationView.addHeaderView(headerView);

        header = (LinearLayout) headerView.findViewById(R.id.ll_profile);
        header.setOnClickListener(this);

        context = getApplicationContext();
        txtemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navheader_email);
        txtname  = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navheader_name);

        sesssionManager = new SessionManager(context);

        /*
        * check if user has logged in this device before
        */
        if (!sesssionManager.isUserLoggedIn()) {
            this.doChangeActivity(context, AuthActivity.class);
        } else {
            this.setTitle(R.string.app_name);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_xml, new UsersVPContainerFragment()).commit();
            txtemail.setText(sesssionManager.getUserLoggedIn().getEmail());
            txtname.setText(sesssionManager.getUserLoggedIn().getName());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent _intent = new Intent(getApplicationContext(), SearchActivity.class);
            _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(_intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_users) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_xml, new UsersVPContainerFragment()).commit();
        }
        else if (id == R.id.nav_logout) {
            sesssionManager.doClearSession();
            if (!sesssionManager.isUserLoggedIn()) {
                this.doChangeActivity(context, AuthActivity.class);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ll_profile:
                Log.d("tmp", "onClick: ");
                Intent i = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
