package com.example.thejokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayingActivity extends AppCompatActivity {


    private TextView theJokeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displaying);

        String joke = getIntent().getStringExtra("TheJoke");
        theJokeTextView = findViewById(R.id.TheJokeTextView);
        theJokeTextView.setText(joke);
    }
}
