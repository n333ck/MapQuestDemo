package com.rjasso.mapquest.mapquestdemo.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.rjasso.mapquest.mapquestdemo.R;
import com.rjasso.mapquest.mapquestdemo.adapters.InstagramAdapter;
import com.rjasso.mapquest.mapquestdemo.apis.InstagramAPI;

/**
 * This class shows photos and listen for the scrolling to detect whether the user reach the end of the listview.
 * Created by Rafael on 22/03/15.
 *
 */
public class InstagramActivity extends Activity implements AbsListView.OnScrollListener{
    private ListView photosView;
    private static Context applicationContext;

    public static Context getApplicationStaticContext(){
        return applicationContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        applicationContext = getApplicationContext();

        photosView = (ListView) findViewById(R.id.photos);
        InstagramAdapter instagramAdapter = InstagramAdapter.getInstance(photosView);
        photosView.setAdapter( instagramAdapter );
        photosView.setOnScrollListener(this);
        InstagramAPI.addEventsListener(instagramAdapter);
    }

    //Required for OnScrollListener interface
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {}


    /**
     * This method detects the if the user has reached the end of the listview.
     */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if( (firstVisibleItem + visibleItemCount) == totalItemCount)
            InstagramAPI.requestNextPageAsync();
    }
}
