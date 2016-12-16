package com.example.davidbezalellaoli.demohome.presenter;

import com.example.davidbezalellaoli.demohome.model.basic.Response;

/**
 * Created by David Bezalel Laoli on 10/30/2016.
 */
public interface iPresenterResponse {
    public void doSuccess (Response response);
    public void doFail (String message);
    public void doConnectionError (int message);
}
