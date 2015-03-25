package com.rjasso.mapquest.mapquestdemo.utils;

import android.content.Context;

import com.rjasso.mapquest.mapquestdemo.R;
import com.rjasso.mapquest.mapquestdemo.activities.InstagramActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class retrieves the URLs from the JSON
 * Created by Rafael on 22/03/15.
 */
public class DataRetriever {
    private JSONObject mApiResult;
    private Context context;

    public DataRetriever(JSONObject apiResult){
        context = InstagramActivity.getApplicationStaticContext();
        mApiResult = apiResult;
    }

    public List<String> getImagesUrl() throws JSONException{
        JSONArray data = mApiResult.getJSONArray("data");
        int length = data.length();
        ArrayList<String> result = new ArrayList<String>();

        for(int i=0;i<length;i++){
            JSONObject entry = data.getJSONObject(i);
            JSONObject imagesData = entry.getJSONObject(context.getString(R.string.images));
            JSONObject imageData = imagesData.getJSONObject(context.getString(R.string.low_resolution));
            result.add(imageData.getString(context.getString(R.string.url)));
        }
        return result;
    }

    public String getNextPageUrl() throws JSONException{
        JSONObject pagination = mApiResult.getJSONObject(context.getString(R.string.pagination));
        return pagination.getString(context.getString(R.string.next_url));
    }
}
