package com.rjasso.mapquest.mapquestdemo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rjasso.mapquest.mapquestdemo.activities.InstagramActivity;
import com.rjasso.mapquest.mapquestdemo.apis.iInstagramAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 22/03/15.
 *
 * In this adapter class every element is loaded.
 *
 */
public class InstagramAdapter extends BaseAdapter implements iInstagramAPI {
    private static InstagramAdapter instance;
    public static String TAG = InstagramAdapter.class.getSimpleName();

    private List<String> data;
    private Context context;
    private View container;

    private InstagramAdapter(){
        data = new ArrayList<String>();
    }

    public static InstagramAdapter getInstance(View view){
        if(instance == null)
            instance = new InstagramAdapter();
        instance.container = view;
        instance.context = InstagramActivity.getApplicationStaticContext();
        return instance;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view == null){
            imageView = new ImageView(context);
        }
        else imageView = (ImageView) view;

        //We use Picasso library to handle memory and performance.
        Picasso.with(context).load(getItem(i)).into(imageView);
        return imageView;
    }

    /**
     * OnSuccess we load the images
     */
    @Override
    public void onSuccess(List<String> imagesUrl, String nextPageUrl) {
        data.addAll(imagesUrl);
        notifyDataSetChanged();
    }


    @Override
    public void onFail(Exception exception) {
        Log.d(TAG,"onFail("+ exception + ")");
    }
}
