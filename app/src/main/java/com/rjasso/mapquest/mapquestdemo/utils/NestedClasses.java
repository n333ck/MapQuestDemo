package com.rjasso.mapquest.mapquestdemo.utils;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rjasso.mapquest.mapquestdemo.R;
import com.rjasso.mapquest.mapquestdemo.activities.InstagramActivity;

/**
 * This class is to host 2 nested static classes and retain instance successfully.
 * Created by Rafael on 22/03/15.
 */
public class NestedClasses {

    public static class SplashScreenAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialogFragment mFragment;
        int mProgress = 0;

        public void setFragment(ProgressDialogFragment fragment){
            mFragment = fragment;
        }

        @Override
        protected Void doInBackground(Void... params){
            for (int i = 0; i <= 10; i++){
                if (isCancelled())
                    return null;

                SystemClock.sleep(800);
                mProgress = i * 10;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... unused){
            if (mFragment == null)
                return;
            mFragment.updateProgress(mProgress);
        }

        @Override
        protected void onPostExecute(Void unused)
        {
            if (mFragment == null)
                return;
            mFragment.taskFinished();
        }
    }

    public static class ProgressDialogFragment extends Fragment
    {
        private SplashScreenAsyncTask mTask;
        private ProgressBar mProgressBar;
        private Intent mInstagramActivityIntent;

        public void setTask(SplashScreenAsyncTask task)
        {
            mTask = task;
            mTask.setFragment(this);
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);


            if (mTask == null) {
                mTask = new SplashScreenAsyncTask();
                setTask(mTask);
                mInstagramActivityIntent = new Intent(getActivity(), InstagramActivity.class);
            }
            mTask.execute();


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.fragment_task, container);
            mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);

            return view;
        }


        public void updateProgress(int percent){
            mProgressBar.setProgress(percent);
        }

        public void taskFinished(){
            mProgressBar.setProgress(mProgressBar.getMax());
            startActivity(mInstagramActivityIntent);
            getActivity().finish();

        }
    }
}