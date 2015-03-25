package com.rjasso.mapquest.mapquestdemo.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rjasso.mapquest.mapquestdemo.apis.InstagramAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * This class is used to implement and handle the Volley events.
 * Created by Rafael on 22/03/15.
 */
public class VolleyEventImp implements Response.Listener<JSONObject>, Response.ErrorListener{
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        InstagramAPI.dispatchFailEvent(volleyError);
    }

    @Override
    public void onResponse(JSONObject ApiResult) {
        DataRetriever dataRetriever = new DataRetriever(ApiResult);
        try{
            List<String> imagesUrl = dataRetriever.getImagesUrl();
            String nextPageUrl = dataRetriever.getNextPageUrl();
            InstagramAPI.dispatchSuccessEvent(imagesUrl, nextPageUrl);
        }catch (JSONException exception){
            InstagramAPI.dispatchFailEvent(exception);
        }
    }
}
