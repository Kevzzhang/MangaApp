package com.example.davidbezalellaoli.demohome.model.responses;

import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.basic.User;

import java.util.List;

/**
 * Created by David Bezalel Laoli on 11/20/2016.
 */

public class UsersResponse extends Response {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
