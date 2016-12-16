package com.example.davidbezalellaoli.demohome.view.fragments.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.follow.GetFollowingUsersPresenter;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.presenter.users.GetUsersPresenter;
import com.example.davidbezalellaoli.demohome.view.adapters.users.UsersRVAdapter;


public class FollowingFragment extends Fragment implements iPresenterResponse {


    public static RecyclerView rv;
    private SwipeRefreshLayout swipe;
    public static TextView nothing;
    private SessionManager session;
    private GetFollowingUsersPresenter presenter;
    private UsersRVAdapter adapter;

    public FollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_following, container, false);
        rv = (RecyclerView) _view.findViewById(R.id.following_rv);
        swipe = (SwipeRefreshLayout) _view.findViewById(R.id.following_swipe);
        nothing = (TextView) _view.findViewById(R.id.following_nothing);

        session = new SessionManager(getContext());
        presenter = new GetFollowingUsersPresenter(this);
        adapter = new UsersRVAdapter(UsersRVAdapter.FLAG_FOLLOWING_FRAGMENT);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        presenter.getfollowingusers(session.getUserLoggedIn().getId());

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getfollowingusers(session.getUserLoggedIn().getId());
            }
        });

        return _view;
    }
    @Override
    public void doSuccess(Response response) {
        UsersResponse _usersresponse = (UsersResponse) response;
        rv.setVisibility(View.VISIBLE);
        nothing.setVisibility(View.GONE);
        adapter.setUsers(_usersresponse.getUsers());
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(false);

    }

    @Override
    public void doFail(String message) {
        rv.setVisibility(View.GONE);
        nothing.setText(message);
        nothing.setVisibility(View.VISIBLE);
        swipe.setRefreshing(false);

    }

    @Override
    public void doConnectionError(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        swipe.setRefreshing(false);
    }
}
