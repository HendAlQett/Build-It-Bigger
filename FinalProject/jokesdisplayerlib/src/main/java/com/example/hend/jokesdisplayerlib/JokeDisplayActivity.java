package com.example.hend.jokesdisplayerlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static String JOKE_KEY = "joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        TextView tvJokeDisplay = (TextView) findViewById(R.id.tvJokeDisplay);
        String joke = getIntent().getStringExtra(JOKE_KEY);
        if (joke != null && joke.length() != 0) {
            tvJokeDisplay.setText(joke);
        }


    }
}
