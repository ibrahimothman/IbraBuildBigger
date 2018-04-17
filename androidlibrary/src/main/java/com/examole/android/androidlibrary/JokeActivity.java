package com.examole.android.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String TAG = JokeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"insideJokeActivity");
        TextView jokeTxtView = (TextView)findViewById(R.id.joke_textview);

        Intent intent = getIntent();
        if(intent != null && intent.getStringExtra("JOKE_EXTRA") != null){
            String joke = intent.getStringExtra("JOKE_EXTRA");
            Log.d(TAG,"jokeIs "+joke);
            jokeTxtView.setText(joke);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
