package com.example.davidbezalellaoli.demohome.model.responses;

import com.example.davidbezalellaoli.demohome.model.basic.Response;
import com.example.davidbezalellaoli.demohome.model.basic.User;

/**
 * Created by David Bezalel Laoli on 10/14/2016.
 */
public class UserResponse extends Response {

    /*
    * this class allows you to get response based on User.class and Response.class
    */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
}
