package com.example.davidbezalellaoli.demohome.view.fragments.users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.basic.User;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.presenter.users.GetUsersPresenter;
import com.example.davidbezalellaoli.demohome.view.adapters.users.UsersRVAdapter;
import com.example.davidbezalellaoli.demohome.view.adapters.users.UsersVPAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment implements iPresenterResponse {

    RecyclerView rv;
    SwipeRefreshLayout swipe;

    UsersRVAdapter adapter;

    GetUsersPresenter presenter;
    List<User> users;
    SessionManager session;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_users, container, false);

        rv = (RecyclerView) _view.findViewById(R.id.users_rv);
        swipe = (SwipeRefreshLayout) _view.findViewById(R.id.users_swipe);

        presenter = new GetUsersPresenter(this);
        session = new SessionManager(getContext());
        adapter = new UsersRVAdapter(UsersRVAdapter.FLAG_USERS_FRAGMENT);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.getAllUsers(session.getUserLoggedIn().getId(), "");

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllUsers(session.getUserLoggedIn().getId(), "");
            }
        });

        return _view;
    }

    @Override
    public void doSuccess(Response response) {
        UsersResponse _response = (UsersResponse) response;
        users = _response.getUsers();
        adapter.setUsers(users);
        rv.setAdapter(adapter);
        swipe.setRefreshing(false);
    }

    @Override
    public void doFail(String message) {
        swipe.setRefreshing(false);
    }

    @Override
    public void doConnectionError(int message) {
        Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
        swipe.setRefreshing(false);

    }
}
