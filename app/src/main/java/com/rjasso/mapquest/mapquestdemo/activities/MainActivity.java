package com.rjasso.mapquest.mapquestdemo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.rjasso.mapquest.mapquestdemo.R;

/**
 * Initial activity that loads the retained fragment.
 * Created by Rafael on 22/03/15.
 */
public class MainActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
    }
}
