package com.example.davidbezalellaoli.demohome.view.adapters.users;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.demohome.R;
import com.example.davidbezalellaoli.demohome.model.SessionManager.SessionManager;
import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.basic.User;
import com.example.davidbezalellaoli.demohome.model.responses.UserResponse;
import com.example.davidbezalellaoli.demohome.model.responses.UsersResponse;
import com.example.davidbezalellaoli.demohome.presenter.follow.FollowPresenter;
import com.example.davidbezalellaoli.demohome.presenter.iPresenterResponse;
import com.example.davidbezalellaoli.demohome.presenter.users.GetUsersPresenter;
import com.example.davidbezalellaoli.demohome.view.fragments.users.FollowingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Bezalel Laoli on 11/20/2016.
 */

public class UsersRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements iPresenterResponse {

    List<User> users;
    Context context;
    private FollowPresenter presenter;
    private GetUsersPresenter userspresesnter;
    private SessionManager session;
    int flag;

    public static final int FLAG_USERS_FRAGMENT = 1;
    public static final int FLAG_FOLLOWING_FRAGMENT = 2;
    public static final int FLAG_FOLLOWER_FRAGMENT = 3;

    public UsersRVAdapter (int flag) {
        this.flag = flag;
        this.users = new ArrayList<>();
    }
    public void setUsers (List<User> users) {
        this.users = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ItemUsersViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        presenter = new FollowPresenter(this);
        session = new SessionManager(holder.itemView.getContext());
        final User _user = users.get(position);
        final ItemUsersViewHolder _holder = (ItemUsersViewHolder) holder;
        _holder.name.setText(_user.getName());
        adjustmentfollowicon(_user, _holder);
        _holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.dofollow(session.getUserLoggedIn().getId(), _user.getId());
                if (flag == FLAG_USERS_FRAGMENT || flag == FLAG_FOLLOWER_FRAGMENT) {
                    _user.setIsfollowed(!_user.getIsfollowed());
                    adjustmentfollowicon(_user, _holder);
                } else if (flag == FLAG_FOLLOWING_FRAGMENT) {
                    users.remove(_user);
                    notifyDataSetChanged();
                    if (getItemCount() == 0) {
                        FollowingFragment.rv.setVisibility(View.GONE);
                        FollowingFragment.nothing.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void adjustmentfollowicon (User _user, ItemUsersViewHolder _holder) {
        if (_user.getIsfollowed()) {
            _holder.icon.setImageResource(R.drawable.ic_followed);
        } else {
            _holder.icon.setImageResource(R.drawable.ic_follow);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void doSuccess(Response response) {
    }

    @Override
    public void doFail(String message) {
    }

    @Override
    public void doConnectionError(int message) {
        if (flag == FLAG_USERS_FRAGMENT || flag == FLAG_FOLLOWER_FRAGMENT) {
            userspresesnter = this.userspresenterinstance();
            userspresesnter.getAllUsers(session.getUserLoggedIn().getId(), "");
        }
        notifyDataSetChanged();
        Toast.makeText(context, "Fail to follow user.", Toast.LENGTH_SHORT).show();
    }

    private GetUsersPresenter userspresenterinstance () {
        userspresesnter = new GetUsersPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                UsersResponse _users = (UsersResponse) response;
                users = _users.getUsers();
                notifyDataSetChanged();
            }

            @Override
            public void doFail(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doConnectionError(int message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
        return userspresesnter;
    }

    private class ItemUsersViewHolder extends RecyclerView.ViewHolder {
        private ImageView image, icon;
        private TextView name;

        public ItemUsersViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.itemuser_image);
            icon = (ImageView) itemView.findViewById(R.id.itemuser_iconfollow);
            name = (TextView) itemView.findViewById(R.id.itemuser_name);

        }
    }
}
