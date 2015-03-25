package com.rjasso.mapquest.mapquestdemo.apis;

import java.util.List;

/**
 * Interface to handle fail or sucess events on Instagram API.
 * Created by Rafael on 22/03/15.
 */
public interface iInstagramAPI {
    public void onSuccess(List<String> imagesUrl, String nextPageUrl);
    public void onFail(Exception exception);
}
