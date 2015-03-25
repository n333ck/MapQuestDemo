package com.rjasso.mapquest.mapquestdemo.apis;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rjasso.mapquest.mapquestdemo.R;
import com.rjasso.mapquest.mapquestdemo.activities.InstagramActivity;
import com.rjasso.mapquest.mapquestdemo.utils.VolleyEventImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 22/03/15.
 * 
 * This class Wraps the Instagram API to get the images URLs
 */
public class InstagramAPI{
    static String endPointURL = InstagramActivity.getApplicationStaticContext().getString(R.string.instagram_endpoint);
    static List<iInstagramAPI> listeners;
    static boolean stillLoading;
    private static RequestQueue mRequestQueue;

    static {
        stillLoading = false;
        listeners = new ArrayList<iInstagramAPI>();
    }

    private static RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(InstagramActivity.getApplicationStaticContext());
        }
        return mRequestQueue;
    }

    private static <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static void addEventsListener(iInstagramAPI iInstagramAPI){
        if(listeners.indexOf(iInstagramAPI) > -1) return;
        listeners.add(iInstagramAPI);
    }

    public static void dispatchSuccessEvent(List<String> imagesUrl, String nextPageUrl){
        stillLoading = false;
        endPointURL = nextPageUrl;
        for (iInstagramAPI iInstagramAPI : listeners)
            iInstagramAPI.onSuccess(imagesUrl, nextPageUrl);
    }

    public static void dispatchFailEvent(Exception exception){
        stillLoading = false;
        for (iInstagramAPI iInstagramAPI : listeners)
            iInstagramAPI.onFail(exception);
    }

    /**
     * This method request more images when is needed.
     */
    public static void requestNextPageAsync(){
        if(stillLoading)return;
        stillLoading = true;
        VolleyEventImp volleyEventImp = new VolleyEventImp();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPointURL, null, volleyEventImp, volleyEventImp);
        addToRequestQueue(request);
    }
}


