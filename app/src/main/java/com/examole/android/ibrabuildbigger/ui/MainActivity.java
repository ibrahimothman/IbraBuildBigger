package com.examole.android.ibrabuildbigger.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import com.examole.android.androidlibrary.JokeActivity;
import com.examole.android.ibrabuildbigger.BuildConfig;
import com.examole.android.ibrabuildbigger.R;
import callback.Callbacks;

import data.Constant;
import data.EndpointAsyncTask;

public class MainActivity extends AppCompatActivity implements Callbacks.EndpointAsyncTaskCallback,
        Callbacks.InterstitialAdCallback, Callbacks.JokeButtonCallback{

    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean interstitialAdFinished = false;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(savedInstanceState == null){
            MainFragment mainFragment = new MainFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.main_container_fragment,mainFragment)
                    .commit();
        }
    }




    // call when endpoint asyncTask retrieve jokes
    @Override
    public void onEndpointAsyncTaskDone(String result) {
        Log.d(TAG,"AsynDone "+interstitialAdFinished);
        if(result != null && !TextUtils.isEmpty(result)){}
            this.result = result;
        if(BuildConfig.PAID_VERSION)
            startJokeActivity(result);

    }


    // start activity to display jokes
    private void startJokeActivity(String result) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(Constant.JOKE_EXTRA, result);
        startActivity(intent);
    }


    // call when interstitial ad closed
    @Override
    public void onInterstitialClosed() {
        Log.d(TAG,"adClosed ");
        if(result != null){
            startJokeActivity(result);
        }
    }


    // call when joke button clicked
    @Override
    public void onJokeButtonClicked() {
        Log.d(TAG,"youHaveClickedJokeButton");
        new EndpointAsyncTask(this).execute(this);
    }
}
