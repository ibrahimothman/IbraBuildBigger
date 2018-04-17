package com.examole.android.ibrabuildbigger.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.examole.android.ibrabuildbigger.R;
import com.example.MyJokes;

import callback.Callbacks;


/**
 * Created by dell on 4/16/2018.
 */

public class MainFragment extends Fragment {


    private static final String TAG = "MainFragment";
    private Callbacks.JokeButtonCallback jokeButtonCallback;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.free_layout,container,false);
        jokeButtonCallback = (Callbacks.JokeButtonCallback)getActivity();

        Button jokeButton = (Button)view.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"youHaveClickedButton");
                jokeButtonCallback.onJokeButtonClicked();

            }
        });
        return view;
    }





}
