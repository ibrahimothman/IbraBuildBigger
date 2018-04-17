package com.examole.android.ibrabuildbigger.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.examole.android.ibrabuildbigger.BuildConfig;
import com.examole.android.ibrabuildbigger.R;

import callback.Callbacks;
import data.Constant;
import data.EndpointAsyncTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by dell on 4/16/2018.
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private InterstitialAd interstitialAd;
    private Context context;
    private Callbacks.InterstitialAdCallback interstitialAdCallback;
    private Callbacks.JokeButtonCallback jokeButtonCallback;
    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.free_layout,container,false);
        context = getContext();
        interstitialAdCallback = (Callbacks.InterstitialAdCallback)getActivity();
        jokeButtonCallback = (Callbacks.JokeButtonCallback)getActivity();

        // setup banner ad
        AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Button jokeButton = (Button)view.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"youHaveClickedButton");
                jokeButtonCallback.onJokeButtonClicked();
                setupInterstitialAd();

            }
        });


        return view;
    }




//    @Override
//    public void onEndpointAsyncTaskDone(String result) {
//        setupInterstitialAd();
//    }
//
    private void setupInterstitialAd() {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(Constant.INTERSTITIAL_UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAdCallback.onInterstitialClosed();


            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                interstitialAd.loadAd(new AdRequest.Builder().build());
                interstitialAdCallback.onInterstitialClosed();

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }
        });
    }
}
